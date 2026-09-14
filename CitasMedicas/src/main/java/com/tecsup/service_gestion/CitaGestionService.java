package com.tecsup.service_gestion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.tecsup.model.Cita;
import com.tecsup.model_gestion.HistorialCita;
import com.tecsup.repository.CitaRepository;
import com.tecsup.repository_gestion.HistorialCitaRepository;

/**
 * Módulo de Gestión de citas.
 *
 * RF-CIT-09: modificar una cita.
 * RF-CIT-10: reprogramar una cita.
 * RF-CIT-11: cancelar una cita (no se elimina físicamente).
 * RF-CIT-12: registrar el motivo de cancelación o reprogramación.
 * RF-CIT-13: cambiar el estado de la cita.
 */
@Service
public class CitaGestionService {

    public static final String ESTADO_PROGRAMADA = "PROGRAMADA";
    public static final String ESTADO_CONFIRMADA = "CONFIRMADA";
    public static final String ESTADO_EN_ESPERA = "EN ESPERA";
    public static final String ESTADO_EN_ATENCION = "EN ATENCIÓN";
    public static final String ESTADO_ATENDIDA = "ATENDIDA";
    public static final String ESTADO_CANCELADA = "CANCELADA";
    public static final String ESTADO_NO_ASISTIO = "NO ASISTIÓ";

    // Grafo de transiciones válidas, según el flujo descrito en el enunciado (punto 5).
    private static final Map<String, Set<String>> TRANSICIONES = Map.of(
            ESTADO_PROGRAMADA, Set.of(ESTADO_CONFIRMADA, ESTADO_CANCELADA, ESTADO_NO_ASISTIO),
            ESTADO_CONFIRMADA, Set.of(ESTADO_EN_ESPERA, ESTADO_CANCELADA, ESTADO_NO_ASISTIO),
            ESTADO_EN_ESPERA, Set.of(ESTADO_EN_ATENCION),
            ESTADO_EN_ATENCION, Set.of(ESTADO_ATENDIDA)
    );

    private final CitaRepository citaRepository;
    private final HistorialCitaRepository historialRepository;

    public CitaGestionService(CitaRepository citaRepository, HistorialCitaRepository historialRepository) {
        this.citaRepository = citaRepository;
        this.historialRepository = historialRepository;
    }

    public Cita obtener(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada"));
    }

    public List<HistorialCita> historial(Long idCita) {
        obtener(idCita);
        return historialRepository.findByIdCitaOrderByFechaRegistroDesc(idCita);
    }

    /** RF-CIT-09: modificar datos generales de la cita (no cambia el estado). */
    @Transactional
    public Cita modificar(Long id, Cita datos, String usuario) {
        Cita cita = obtener(id);
        rechazarSiEsFinal(cita, "modificar");

        String detalleAnterior = resumen(cita);

        if (!estaVacio(datos.getPaciente())) {
            cita.setPaciente(datos.getPaciente().trim());
        }
        if (!estaVacio(datos.getDni())) {
            cita.setDni(datos.getDni().trim());
        }
        if (!estaVacio(datos.getEspecialidad())) {
            cita.setEspecialidad(datos.getEspecialidad().trim());
        }
        if (!estaVacio(datos.getMedico())) {
            cita.setMedico(datos.getMedico().trim());
        }
        if (datos.getConsultorio() != null) {
            cita.setConsultorio(textoOpcional(datos.getConsultorio()));
        }
        if (datos.getFecha() != null) {
            cita.setFecha(datos.getFecha());
        }
        if (datos.getHora() != null) {
            cita.setHora(datos.getHora());
        }
        if (datos.getTipoAtencion() != null) {
            cita.setTipoAtencion(textoOpcional(datos.getTipoAtencion()));
        }
        if (datos.getObservaciones() != null) {
            cita.setObservaciones(textoOpcional(datos.getObservaciones()));
        }

        Cita guardada = citaRepository.save(cita);
        registrar(guardada, "MODIFICACION", guardada.getEstado(), guardada.getEstado(),
                detalleAnterior, resumen(guardada), null, usuario);
        return guardada;
    }

    /** RF-CIT-10 + RF-CIT-12: reprogramar fecha/hora/médico/consultorio, con motivo obligatorio. */
    @Transactional
    public Cita reprogramar(Long id, LocalDate nuevaFecha, LocalTime nuevaHora, String nuevoMedico,
                             String nuevoConsultorio, String motivo, String usuario) {
        Cita cita = obtener(id);
        rechazarSiEsFinal(cita, "reprogramar");
        if (estaVacio(motivo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El motivo de la reprogramación es obligatorio");
        }

        String detalleAnterior = resumen(cita);
        String estadoAnterior = cita.getEstado();

        if (nuevaFecha != null) {
            cita.setFecha(nuevaFecha);
        }
        if (nuevaHora != null) {
            cita.setHora(nuevaHora);
        }
        if (!estaVacio(nuevoMedico)) {
            cita.setMedico(nuevoMedico.trim());
        }
        if (!estaVacio(nuevoConsultorio)) {
            cita.setConsultorio(nuevoConsultorio.trim());
        }
        cita.setMotivo(motivo.trim());
        // Toda reprogramación vuelve la cita a PROGRAMADA para que siga el flujo normal de confirmación.
        cita.setEstado(ESTADO_PROGRAMADA);

        Cita guardada = citaRepository.save(cita);
        registrar(guardada, "REPROGRAMACION", estadoAnterior, guardada.getEstado(),
                detalleAnterior, resumen(guardada), motivo.trim(), usuario);
        return guardada;
    }

    /** RF-CIT-11 + RF-CIT-12: cancelar sin borrar el registro, guardando el motivo. */
    @Transactional
    public Cita cancelar(Long id, String motivo, String usuario) {
        Cita cita = obtener(id);
        if (ESTADO_CANCELADA.equals(cita.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cita ya está cancelada");
        }
        if (ESTADO_ATENDIDA.equals(cita.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede cancelar una cita ya atendida");
        }
        if (estaVacio(motivo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El motivo de cancelación es obligatorio");
        }

        String estadoAnterior = cita.getEstado();
        cita.setEstado(ESTADO_CANCELADA);
        cita.setMotivo(motivo.trim());

        Cita guardada = citaRepository.save(cita);
        registrar(guardada, "CANCELACION", estadoAnterior, ESTADO_CANCELADA, null, null, motivo.trim(), usuario);
        return guardada;
    }

    /** RF-CIT-13: cambiar el estado siguiendo el flujo permitido. */
    @Transactional
    public Cita cambiarEstado(Long id, String nuevoEstado, String usuario) {
        Cita cita = obtener(id);
        String estadoActual = cita.getEstado();

        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nuevo estado es obligatorio");
        }
        String destino = nuevoEstado.trim().toUpperCase();
        Set<String> permitidos = TRANSICIONES.getOrDefault(estadoActual, Set.of());
        if (!permitidos.contains(destino)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede pasar de " + estadoActual + " a " + destino);
        }

        cita.setEstado(destino);
        Cita guardada = citaRepository.save(cita);
        registrar(guardada, "CAMBIO_ESTADO", estadoActual, destino, null, null, null, usuario);
        return guardada;
    }

    private void rechazarSiEsFinal(Cita cita, String accion) {
        if (ESTADO_CANCELADA.equals(cita.getEstado()) || ESTADO_ATENDIDA.equals(cita.getEstado())
                || ESTADO_NO_ASISTIO.equals(cita.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede " + accion + " una cita en estado " + cita.getEstado());
        }
    }

    private void registrar(Cita cita, String tipoAccion, String estadoAnterior, String estadoNuevo,
                            String detalleAnterior, String detalleNuevo, String motivo, String usuario) {
        HistorialCita h = new HistorialCita();
        h.setIdCita(cita.getIdCita());
        h.setCodigoCita(cita.getCodigoCita());
        h.setTipoAccion(tipoAccion);
        h.setEstadoAnterior(estadoAnterior);
        h.setEstadoNuevo(estadoNuevo);
        h.setDetalleAnterior(detalleAnterior);
        h.setDetalleNuevo(detalleNuevo);
        h.setMotivo(motivo);
        h.setUsuario(estaVacio(usuario) ? "sistema" : usuario.trim());
        h.setFechaRegistro(LocalDateTime.now());
        historialRepository.save(h);
    }

    private String resumen(Cita cita) {
        return "Fecha: " + cita.getFecha() + " " + cita.getHora()
                + " · Médico: " + cita.getMedico()
                + " · Consultorio: " + (cita.getConsultorio() == null ? "-" : cita.getConsultorio());
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }

    private String textoOpcional(String valor) {
        return estaVacio(valor) ? null : valor.trim();
    }
}
