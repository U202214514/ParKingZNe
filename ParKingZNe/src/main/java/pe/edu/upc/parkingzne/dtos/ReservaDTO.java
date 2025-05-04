package pe.edu.upc.parkingzne.dtos;

import pe.edu.upc.parkingzne.entities.Estacionamiento;
import pe.edu.upc.parkingzne.entities.Usuario;

import java.time.LocalDate;

public class ReservaDTO {

    private int idReserva;

    private LocalDate fechaReserva;

    private String estadoReserva;

    private Usuario us;

    private Estacionamiento estacionamiento;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
}
