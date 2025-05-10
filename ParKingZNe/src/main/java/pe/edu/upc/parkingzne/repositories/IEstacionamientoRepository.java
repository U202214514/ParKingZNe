package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Estacionamiento;


import java.util.List;

@Repository
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Integer> {

    @Query(value = "SELECT \n" +
            "    r.id_estacionamiento,\n" +
            "    e.nombre_estacionamiento,\n" +
            "    r.fecha_reserva,\n" +
            "    COUNT(*) AS cantidad_reservas,\n" +
            "    SUM(e.tarifa_hora_estacionamiento) AS total_ganado\n" +
            "FROM \n" +
            "    reserva r\n" +
            "JOIN \n" +
            "    estacionamiento e ON r.id_estacionamiento = e.id_estacionamiento\n" +
            "WHERE \n" +
            "    r.estado_reserva = 'Finalizada'\n" +
            "GROUP BY \n" +
            "    r.id_estacionamiento, e.nombre_estacionamiento, r.fecha_reserva\n" +
            "ORDER BY \n" +
            "    r.fecha_reserva ASC;", nativeQuery = true)
    public List<String[]> recaudacionDiariaxEstacionamiento();
}
