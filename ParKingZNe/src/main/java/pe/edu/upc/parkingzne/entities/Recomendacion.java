package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Recomendacion")
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecomendacion;
    @Column(name="comentario", length = 75, nullable = false)
    private String comentario;
    @Column(name="calificacion", length = 30)
    private String calificacion;
    @Column(name="fechaRecomendacion", nullable = false)
    private LocalDate fechaRecomendacion;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario us;
    @ManyToOne
    @JoinColumn(name="idEstacionamiento")
    private Estacionamiento estacionamiento;

    public Recomendacion() {

    }

    public Recomendacion(int idRecomendacion, String comentario, String calificacion, LocalDate fechaRecomendacion, Usuario us, Estacionamiento estacionamiento) {
        this.idRecomendacion = idRecomendacion;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fechaRecomendacion = fechaRecomendacion;
        this.us = us;
        this.estacionamiento = estacionamiento;
    }

    public int getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFechaRecomendacion() {
        return fechaRecomendacion;
    }

    public void setFechaRecomendacion(LocalDate fechaRecomendacion) {
        this.fechaRecomendacion = fechaRecomendacion;
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
