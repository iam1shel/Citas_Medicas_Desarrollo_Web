package com.tecsup.model_gestion;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Registra el historial de cambios de una cita (RF-CIT-09, RF-CIT-10, RF-CIT-11,
 * RF-CIT-12, RF-CIT-13). No se elimina ninguna cita físicamente: cada acción de
 * gestión queda guardada aquí junto con el motivo y el usuario que la realizó.
 */
@Entity
@Table(name = "historial_cita")
public class HistorialCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long idHistorial;

    @Column(name = "id_cita", nullable = false)
    private Long idCita;

    @Column(name = "codigo_cita", length = 20)
    private String codigoCita;

    // MODIFICACION | REPROGRAMACION | CANCELACION | CAMBIO_ESTADO
    @Column(name = "tipo_accion", nullable = false, length = 30)
    private String tipoAccion;

    @Column(name = "estado_anterior", length = 30)
    private String estadoAnterior;

    @Column(name = "estado_nuevo", length = 30)
    private String estadoNuevo;

    @Column(name = "detalle_anterior", length = 255)
    private String detalleAnterior;

    @Column(name = "detalle_nuevo", length = 255)
    private String detalleNuevo;

    @Column(length = 255)
    private String motivo;

    @Column(length = 80)
    private String usuario;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    public HistorialCita() {
    }

    public Long getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(String estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public String getDetalleAnterior() {
        return detalleAnterior;
    }

    public void setDetalleAnterior(String detalleAnterior) {
        this.detalleAnterior = detalleAnterior;
    }

    public String getDetalleNuevo() {
        return detalleNuevo;
    }

    public void setDetalleNuevo(String detalleNuevo) {
        this.detalleNuevo = detalleNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
