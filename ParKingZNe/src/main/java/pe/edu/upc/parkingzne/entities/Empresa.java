package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpresa;
    @Column(name="nombreEmpresa",length=50,nullable=false)
    private String nombreEmpresa;
    @Column(name="direccionEmpresa",length=50,nullable=false)
    private String direccionEmpresa;
    @Column(name="tipoEmpresa",length=50,nullable=false)
    private String tipoEmpresa;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario us;

    public Empresa() {

    }

    public Empresa(int idEmpresa, String nombreEmpresa, String direccionEmpresa, String tipoEmpresa, Usuario us) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.tipoEmpresa = tipoEmpresa;
        this.us = us;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }
}
