package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.NotificacionReserva;


@Repository
public interface INotificacionReservaRepository extends JpaRepository<NotificacionReserva, Integer> {
}
