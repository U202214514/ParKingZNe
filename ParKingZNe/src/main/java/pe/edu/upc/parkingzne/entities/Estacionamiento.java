package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Estacionamiento")
public class Estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstacionamiento;
    @Column(name = "nombreEstacionamiento", length = 50, nullable = false)
    private String nombreEstacionamiento;
    @Column(name = "direccionEstacionamiento", length = 50, nullable = false)
    private String direccionEstacionamiento;
    @Column(name = "tipoEstacionamiento", length = 20, nullable = false)
    private String tipoEstacionamiento;
    @Column(name = "tarifaHoraEstacionamiento", nullable = false)
    private double tarifaHoraEstacionamiento;
    @Column(name = "capacidadEstacionamiento", length = 50, nullable = false)
    private String capacidadEstacionamiento;
    @Column(name = "estadoEstacionamiento", length = 20, nullable = false)
    private String estadoEstacionamiento;
    @Column(name = "latitudEstacionamiento",nullable = false)
    private double latitudEstacionamiento;
    @Column(name = "longitudEstacionamiento",nullable = false)
    private double longitudEstacionamiento;

    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    public Estacionamiento() {

    }

    public Estacionamiento(int idEstacionamiento, String nombreEstacionamiento, String direccionEstacionamiento, String tipoEstacionamiento, double tarifaHoraEstacionamiento, String capacidadEstacionamiento, String estadoEstacionamiento, double latitudEstacionamiento, double longitudEstacionamiento, Empresa empresa) {
        this.idEstacionamiento = idEstacionamiento;
        this.nombreEstacionamiento = nombreEstacionamiento;
        this.direccionEstacionamiento = direccionEstacionamiento;
        this.tipoEstacionamiento = tipoEstacionamiento;
        this.tarifaHoraEstacionamiento = tarifaHoraEstacionamiento;
        this.capacidadEstacionamiento = capacidadEstacionamiento;
        this.estadoEstacionamiento = estadoEstacionamiento;
        this.latitudEstacionamiento = latitudEstacionamiento;
        this.longitudEstacionamiento = longitudEstacionamiento;
        this.empresa = empresa;
    }

    public int getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(int idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public String getNombreEstacionamiento() {
        return nombreEstacionamiento;
    }

    public void setNombreEstacionamiento(String nombreEstacionamiento) {
        this.nombreEstacionamiento = nombreEstacionamiento;
    }

    public String getDireccionEstacionamiento() {
        return direccionEstacionamiento;
    }

    public void setDireccionEstacionamiento(String direccionEstacionamiento) {
        this.direccionEstacionamiento = direccionEstacionamiento;
    }

    public String getTipoEstacionamiento() {
        return tipoEstacionamiento;
    }

    public void setTipoEstacionamiento(String tipoEstacionamiento) {
        this.tipoEstacionamiento = tipoEstacionamiento;
    }

    public double getTarifaHoraEstacionamiento() {
        return tarifaHoraEstacionamiento;
    }

    public void setTarifaHoraEstacionamiento(double tarifaHoraEstacionamiento) {
        this.tarifaHoraEstacionamiento = tarifaHoraEstacionamiento;
    }

    public String getCapacidadEstacionamiento() {
        return capacidadEstacionamiento;
    }

    public void setCapacidadEstacionamiento(String capacidadEstacionamiento) {
        this.capacidadEstacionamiento = capacidadEstacionamiento;
    }

    public String getEstadoEstacionamiento() {
        return estadoEstacionamiento;
    }

    public void setEstadoEstacionamiento(String estadoEstacionamiento) {
        this.estadoEstacionamiento = estadoEstacionamiento;
    }

    public double getLatitudEstacionamiento() {
        return latitudEstacionamiento;
    }

    public void setLatitudEstacionamiento(double latitudEstacionamiento) {
        this.latitudEstacionamiento = latitudEstacionamiento;
    }

    public double getLongitudEstacionamiento() {
        return longitudEstacionamiento;
    }

    public void setLongitudEstacionamiento(double longitudEstacionamiento) {
        this.longitudEstacionamiento = longitudEstacionamiento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
