package pe.edu.upc.parkingzne.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "Usuario")

public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(name = "nombre", nullable=false, length = 50)
    private String nombre;

    @Column(name = "apellidos", nullable=false, length = 50)
    private String apellidos;

    @Column(name = "fechanacimiento", nullable = false )
    private LocalDate fechanacimiento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "correo", nullable=false, length = 50)
    private String correo;

    @Column(name = "placaAuto", length = 10, nullable = true)
    private String placaAuto;

    @Column(name = "latitudUsuario", nullable=false )
    private double latitudUsuario;

    @Column(name = "longitudUsuario", nullable=false )
    private double longitudUsuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "telefono", nullable=false, length = 12)
    private String telefono;

    @Column(name = "username", unique = true, length = 20)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable=false, length = 60)
    private String password;
    private Boolean enabled;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = true)
    private List<Rol>roles;

    public Usuario(int id_usuario, String nombre, String apellidos, LocalDate fechanacimiento, String correo, String placaAuto, double latitudUsuario, double longitudUsuario, String telefono, String username, String password, Boolean enabled, List<Rol> roles) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.correo = correo;
        this.placaAuto = placaAuto;
        this.latitudUsuario = latitudUsuario;
        this.longitudUsuario = longitudUsuario;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPlacaAuto() {
        return placaAuto;
    }

    public void setPlacaAuto(String placaAuto) {
        this.placaAuto = placaAuto;
    }

    public double getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(double latitudUsuario) {
        this.latitudUsuario = latitudUsuario;
    }

    public double getLongitudUsuario() {
        return longitudUsuario;
    }

    public void setLongitudUsuario(double longitudUsuario) {
        this.longitudUsuario = longitudUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
