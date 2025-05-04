package pe.edu.upc.parkingzne.dtos;

import pe.edu.upc.parkingzne.entities.Reclamo;

import java.time.LocalDate;
import java.time.LocalTime;

public class RespuestaDTO {

    private int idRespuesta;

    private LocalDate fechaRespuesta;

    private LocalTime horaRespuesta;

    private Reclamo reclamo;

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public LocalDate getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDate fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public LocalTime getHoraRespuesta() {
        return horaRespuesta;
    }

    public void setHoraRespuesta(LocalTime horaRespuesta) {
        this.horaRespuesta = horaRespuesta;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }
}
