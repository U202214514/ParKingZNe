package pe.edu.upc.parkingzne.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "NotificacionReserva")
public class NotificacionReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotificacionReserva;
    @ManyToOne
    @JoinColumn(name = "idReserva")
    private Reserva reserva;

    public NotificacionReserva() {

    }

    public NotificacionReserva(int idNotificacionReserva, Reserva reserva) {
        this.idNotificacionReserva = idNotificacionReserva;
        this.reserva = reserva;
    }

    public int getIdNotificacionReserva() {
        return idNotificacionReserva;
    }

    public void setIdNotificacionReserva(int idNotificacionReserva) {
        this.idNotificacionReserva = idNotificacionReserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}