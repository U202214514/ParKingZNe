package pe.edu.upc.parkingzne.dtos;

public class ReservasActivasUsuarioDTO {

    private String username;

    private String apellido;

    private int cantidadReservasActivas;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCantidadReservasActivas() {
        return cantidadReservasActivas;
    }

    public void setCantidadReservasActivas(int cantidadReservasActivas) {
        this.cantidadReservasActivas = cantidadReservasActivas;
    }
}
