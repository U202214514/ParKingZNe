package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Reserva;


import java.util.List;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = "SELECT u.username, u.apellidos, r.fecha_reserva, r.estado_reserva " +
            "FROM reserva r " +
            "INNER JOIN Usuario u ON r.id_usuario = u.id_usuario", nativeQuery = true)
    public List<String[]> listarReservasPorUsuario();

    @Query(value = "SELECT u.username, u.apellidos, COUNT(*) AS cantidad_reservas_activas " +
            "FROM reserva r " +
            "JOIN usuario u ON r.id_usuario = u.id_usuario " +
            "WHERE r.estado_reserva = 'Reservada' " +
            "GROUP BY u.username, u.apellidos", nativeQuery = true)
    public List<Object[]> listarCantidadReservasActivasPorUsuario();
}
