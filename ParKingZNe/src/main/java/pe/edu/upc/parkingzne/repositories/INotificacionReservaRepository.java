package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.NotificacionReserva;

import java.util.List;


@Repository
public interface INotificacionReservaRepository extends JpaRepository<NotificacionReserva, Integer> {

    @Query(value = "SELECT r.estado_reserva AS TIPOS_RESERVA, COUNT(n.id_notificacion_reserva) AS CANTIDAD \n" +
            "FROM public.notificacion_reserva n \n" +
            "JOIN public.reserva r ON n.id_reserva = r.id_reserva \n" +
            "GROUP BY r.estado_reserva;", nativeQuery = true)
    public List<Object[]> CantidaddeNotificionesPorReservas();
}
