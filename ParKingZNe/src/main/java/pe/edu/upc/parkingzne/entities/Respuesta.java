package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRespuesta;
    @Column(name="fechaRespuesta",nullable=false)
    private LocalDate fechaRespuesta;
    @Column(name="horaRespuesta",nullable=false)
    private LocalTime horaRespuesta;

    @ManyToOne
    @JoinColumn(name = "idReclamo")
    private Reclamo reclamo;

    public Respuesta() {

    }

    public Respuesta(int idRespuesta, LocalDate fechaRespuesta, LocalTime horaRespuesta, Reclamo reclamo) {
        this.idRespuesta = idRespuesta;
        this.fechaRespuesta = fechaRespuesta;
        this.horaRespuesta = horaRespuesta;
        this.reclamo = reclamo;
    }

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
