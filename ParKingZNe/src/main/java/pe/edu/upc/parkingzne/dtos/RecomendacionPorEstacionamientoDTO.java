package pe.edu.upc.parkingzne.dtos;

public class RecomendacionPorEstacionamientoDTO {

    private String Estacionamiento_Recomendado;
    private int cantidad_calificaciones;

    public String getEstacionamiento_Recomendado() {
        return Estacionamiento_Recomendado;
    }

    public void setEstacionamiento_Recomendado(String estacionamiento_Recomendado) {
        Estacionamiento_Recomendado = estacionamiento_Recomendado;
    }

    public int getCantidad_calificaciones() {
        return cantidad_calificaciones;
    }

    public void setCantidad_calificaciones(int cantidad_calificaciones) {
        this.cantidad_calificaciones = cantidad_calificaciones;
    }
}
