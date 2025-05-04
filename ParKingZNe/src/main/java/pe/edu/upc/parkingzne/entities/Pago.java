package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;
    @Column(name="montoPago", nullable=false)
    private double montoPago;
    @Column(name="fechaPago", nullable=false)
    private LocalDate fechaPago;
    @Column(name="estadoPago", length = 50, nullable=false)
    private String estadoPago;
    @ManyToOne
    @JoinColumn(name="idSuscripcion")
    private Suscripcion suscripcion;

    public Pago(){

    }

    public Pago(int idPago, double montoPago, LocalDate fechaPago, String estadoPago, Suscripcion suscripcion) {
        this.idPago = idPago;
        this.montoPago = montoPago;
        this.fechaPago = fechaPago;
        this.estadoPago = estadoPago;
        this.suscripcion = suscripcion;
    }

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
