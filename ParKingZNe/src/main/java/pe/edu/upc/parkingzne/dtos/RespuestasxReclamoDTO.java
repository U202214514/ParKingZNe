package pe.edu.upc.parkingzne.dtos;

public class RespuestasxReclamoDTO {

    private int cantidadRespuestas;
    private String titulo_reclamo;
    private String username;

    public int getCantidadRespuestas() {
        return cantidadRespuestas;
    }

    public void setCantidadRespuestas(int cantidadRespuestas) {
        this.cantidadRespuestas = cantidadRespuestas;
    }

    public String getTitulo_reclamo() {
        return titulo_reclamo;
    }

    public void setTitulo_reclamo(String titulo_reclamo) {
        this.titulo_reclamo = titulo_reclamo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
