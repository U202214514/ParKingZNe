package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Reclamo")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReclamo;
    @Column(name="titulo",length=45, nullable=false)
    private String titulo;
    @Column(name="descripcion",length=75, nullable=false)
    private String descripcion;
    @Column(name = "archivo", nullable = false)
    private Boolean archivo;
    @Column(name="fecha",nullable=false)
    private LocalDate fecha;
    @Column(name="estado",length=20, nullable=false)
    private String estado;
    @Column(name="hora",nullable=false)
    private LocalTime hora;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario us;
    @ManyToOne
    @JoinColumn(name="idReserva")
    private Reserva reserva;

    public Reclamo() {

    }

    public Reclamo(int idReclamo, String titulo, String descripcion, Boolean archivo, LocalDate fecha, String estado, LocalTime hora, Usuario us, Reserva reserva) {
        this.idReclamo = idReclamo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.fecha = fecha;
        this.estado = estado;
        this.hora = hora;
        this.us = us;
        this.reserva = reserva;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getArchivo() {
        return archivo;
    }

    public void setArchivo(Boolean archivo) {
        this.archivo = archivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
