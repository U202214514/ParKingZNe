package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

@Entity
@Table(name="TipoNotificacion")
public class TipoNotificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoNotificacion;
    @Column(name="Descripcion",length=75, nullable=false)
    private String Descripcion;

    public TipoNotificacion() {

    }

    public TipoNotificacion(int idTipoNotificacion, String descripcion) {
        this.idTipoNotificacion = idTipoNotificacion;
        Descripcion = descripcion;
    }

    public int getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(int idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
