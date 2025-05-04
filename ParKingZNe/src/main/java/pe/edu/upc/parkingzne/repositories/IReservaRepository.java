package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Reserva;


import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = "SELECT u.username, u.apellido, r.fecha_reserva, r.estado_reserva " +
            "FROM reserva r " +
            "INNER JOIN users_table u ON r.user_id = u.id", nativeQuery = true)
    public List<String[]> listarReservasPorUsuario();

    @Query(value = "SELECT u.username, u.apellido, es.nombreEstacionamiento AS estacionamiento, r.fecha_reserva, COUNT(*) AS cantidad_reservas \n" +
            "FROM reserva r \n" +
            "INNER JOIN Usuario u ON r.id_usuario = u.id_usuario \n" +
            "INNER JOIN estacionamiento es ON r.id_estacionamiento = es.id_estacionamiento \n" +
            "GROUP BY u.username, u.apellido, es.nombre, r.fecha_reserva \n" +
            "HAVING COUNT(*) > 1;", nativeQuery = true)
    public List<String[]> listarReservasDuplicadas();

    @Query(value = "SELECT u.username, u.apellido, COUNT(*) AS cantidad_reservas_activas \n" +
            "FROM reserva r \n" +
            "INNER JOIN users_table u ON r.user_id = u.id \n" +
            "WHERE r.estado_reserva = 'ACTIVO' \n" +
            "GROUP BY u.username, u.apellido; \n", nativeQuery = true)
    public List<String[]> listarCantidadReservasActivasPorUsuario();
}
