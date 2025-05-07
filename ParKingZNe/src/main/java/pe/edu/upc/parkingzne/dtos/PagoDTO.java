package pe.edu.upc.parkingzne.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pe.edu.upc.parkingzne.entities.Suscripcion;

import java.time.LocalDate;


public class PagoDTO {

    private int idPago;

    @Positive(message = "El monto debe ser mayor que 0")
    private double montoPago;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDate fechaPago;

    @NotNull(message = "El estado de pago debe ser obligatorio")
    private String estadoPago;

    @NotNull(message = "El id del suscriptor es obligatorio")
    private Suscripcion suscripcion;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }
}
