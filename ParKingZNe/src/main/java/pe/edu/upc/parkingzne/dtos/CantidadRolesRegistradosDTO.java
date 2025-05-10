package pe.edu.upc.parkingzne.dtos;

public class CantidadRolesRegistradosDTO {
    private String nombre_del_rol;
    private int cantidad;

    public String getNombre_del_rol() {
        return nombre_del_rol;
    }

    public void setNombre_del_rol(String nombre_del_rol) {
        this.nombre_del_rol = nombre_del_rol;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
