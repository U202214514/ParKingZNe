package pe.edu.upc.parkingzne.dtos;

import pe.edu.upc.parkingzne.entities.Reclamo;
import pe.edu.upc.parkingzne.entities.Reserva;
import pe.edu.upc.parkingzne.entities.TipoNotificacion;
import pe.edu.upc.parkingzne.entities.Usuario;

import java.time.LocalDate;

public class NotificacionDTO {

    private int idNotificacion;

    private String mensaje;

    private LocalDate fechaEmision;

    private LocalDate fechaProgramada;

    private String estado;

    private Usuario us;

    private TipoNotificacion tipoNotificacion;

    private Reserva reserva;

    private Reclamo reclamo;

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
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

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }
}
