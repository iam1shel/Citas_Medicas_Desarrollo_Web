package com.tecsup.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.tecsup.model.Cita;
import com.tecsup.repository.CitaRepository;

@Service
public class CitaService {

    public static final String ESTADO_PROGRAMADA = "PROGRAMADA";

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    public Cita obtener(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public Cita obtenerPorCodigo(String codigo) {
        return citaRepository.findByCodigoCita(codigo).orElse(null);
    }

    @Transactional
    public Cita registrar(Cita datos) {
        validar(datos);

        Cita cita = new Cita();
        cita.setPaciente(datos.getPaciente().trim());
        cita.setDni(datos.getDni().trim());
        cita.setEspecialidad(datos.getEspecialidad().trim());
        cita.setMedico(datos.getMedico().trim());
        cita.setConsultorio(textoOpcional(datos.getConsultorio()));
        cita.setFecha(datos.getFecha());
        cita.setHora(datos.getHora());
        cita.setTipoAtencion(textoOpcional(datos.getTipoAtencion()));
        cita.setMotivo(textoOpcional(datos.getMotivo()));
        cita.setObservaciones(textoOpcional(datos.getObservaciones()));
        cita.setEstado(ESTADO_PROGRAMADA);
        cita.setCodigoCita(generarCodigoUnico());

        return citaRepository.save(cita);
    }

    String generarCodigoUnico() {
        long siguiente = citaRepository.obtenerUltimoId() + 1;
        String codigo = formatearCodigo(siguiente);
        while (citaRepository.existsByCodigoCita(codigo)) {
            siguiente++;
            codigo = formatearCodigo(siguiente);
        }
        return codigo;
    }

    private String formatearCodigo(long numero) {
        return String.format("CIT-%06d", numero);
    }

    private void validar(Cita datos) {
        if (estaVacio(datos.getPaciente())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El paciente es obligatorio");
        }
        if (estaVacio(datos.getDni())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El DNI es obligatorio");
        }
        if (estaVacio(datos.getEspecialidad())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La especialidad es obligatoria");
        }
        if (estaVacio(datos.getMedico())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El médico es obligatorio");
        }
        if (datos.getFecha() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha es obligatoria");
        }
        if (datos.getHora() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La hora es obligatoria");
        }
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }

    private String textoOpcional(String valor) {
        if (estaVacio(valor)) {
            return null;
        }
        return valor.trim();
    }
}
