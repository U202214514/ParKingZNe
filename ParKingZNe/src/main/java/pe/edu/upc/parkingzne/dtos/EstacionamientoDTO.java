package pe.edu.upc.parkingzne.dtos;

import pe.edu.upc.parkingzne.entities.Empresa;

public class EstacionamientoDTO {

    private int idEstacionamiento;

    private String nombreEstacionamiento;

    private String direccionEstacionamiento;

    private String tipoEstacionamiento;

    private double tarifaHoraEstacionamiento;

    private String capacidadEstacionamiento;

    private String estadoEstacionamiento;

    private double latitudEstacionamiento;

    private double longitudEstacionamiento;

    private Empresa empresa;

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
