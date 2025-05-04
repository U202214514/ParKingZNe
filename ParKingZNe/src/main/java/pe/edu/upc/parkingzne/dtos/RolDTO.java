package pe.edu.upc.parkingzne.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import pe.edu.upc.parkingzne.entities.Usuario;

public class RolDTO {
    private int idRol;
    private String tipoRol;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario us;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }
}
