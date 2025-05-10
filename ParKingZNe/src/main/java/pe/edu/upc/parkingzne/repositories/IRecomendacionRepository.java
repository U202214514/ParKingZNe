package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Recomendacion;

import java.util.List;


@Repository
public interface IRecomendacionRepository extends JpaRepository<Recomendacion, Integer> {

    @Query(value = "SELECT \n" +
            "    e.nombre_estacionamiento AS Estacionamiento_Recomendado,\n" +
            "    COUNT(r.id_recomendacion) AS cantidad_calificaciones\n" +
            "FROM \n" +
            "    recomendacion r\n" +
            "JOIN \n" +
            "    estacionamiento e ON r.id_estacionamiento = e.id_estacionamiento\n" +
            "GROUP BY \n" +
            "    e.nombre_estacionamiento\n" +
            "ORDER BY \n" +
            "    cantidad_calificaciones DESC;", nativeQuery = true)
    public List<Object[]> cantidadRecomendacionesEstacionamientos();
}
