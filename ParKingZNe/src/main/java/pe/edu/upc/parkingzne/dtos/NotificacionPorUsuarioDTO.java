package pe.edu.upc.parkingzne.dtos;

import java.time.LocalDate;

public class NotificacionPorUsuarioDTO {
    private String tipoDeNotificacion;
    private String mensaje;
    private LocalDate fechaEmision;
    private String estado;
    private int id_usuario;

    public String getTipoDeNotificacion() {
        return tipoDeNotificacion;
    }

    public void setTipoDeNotificacion(String tipoDeNotificacion) {
        this.tipoDeNotificacion = tipoDeNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
