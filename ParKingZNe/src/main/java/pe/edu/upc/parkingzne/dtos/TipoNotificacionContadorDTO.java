package pe.edu.upc.parkingzne.dtos;

public class TipoNotificacionContadorDTO {
    private String tipoNotificacion;
    private int cantidad;

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
