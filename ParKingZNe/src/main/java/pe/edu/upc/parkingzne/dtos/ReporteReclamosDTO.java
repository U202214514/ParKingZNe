package pe.edu.upc.parkingzne.dtos;

import java.time.LocalDate;

public class ReporteReclamosDTO {
    private int id_usuario;
    private String username;
    private LocalDate fecha_reclamo;
    private int cantidad_reclamos;
    private int reclamos_con_reserva;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getFecha_reclamo() {
        return fecha_reclamo;
    }

    public void setFecha_reclamo(LocalDate fecha_reclamo) {
        this.fecha_reclamo = fecha_reclamo;
    }

    public int getCantidad_reclamos() {
        return cantidad_reclamos;
    }

    public void setCantidad_reclamos(int cantidad_reclamos) {
        this.cantidad_reclamos = cantidad_reclamos;
    }

    public int getReclamos_con_reserva() {
        return reclamos_con_reserva;
    }

    public void setReclamos_con_reserva(int reclamos_con_reserva) {
        this.reclamos_con_reserva = reclamos_con_reserva;
    }
}
