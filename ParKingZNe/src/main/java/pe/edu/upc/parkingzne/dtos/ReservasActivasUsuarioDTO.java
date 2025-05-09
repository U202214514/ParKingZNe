package pe.edu.upc.parkingzne.dtos;

public class ReservasActivasUsuarioDTO {

    private String username;

    private String apellidos;

    private int cantidadReservasActivas;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCantidadReservasActivas() {
        return cantidadReservasActivas;
    }

    public void setCantidadReservasActivas(int cantidadReservasActivas) {
        this.cantidadReservasActivas = cantidadReservasActivas;
    }
}
