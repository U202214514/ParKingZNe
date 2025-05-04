package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Respuesta;

import java.util.List;

@Repository
public interface IRespuestaRepository extends JpaRepository<Respuesta, Integer> {

    @Query(value = "SELECT \n" +
            "    COUNT(r.id_respuesta) AS cantidad_respuestas,  \n" +
            "    reclamo.titulo AS titulo_reclamo, \n " +
            "    u.username \n" +
            "FROM \n" +
            "    Respuesta r \n" +
            "JOIN \n" +
            "    Reclamo reclamo ON r.id_reclamo = reclamo.id_reclamo  \n" +
            "JOIN \n" +
            "    Usuario u ON reclamo.id_usuario = u.id_usuario \n" +
            "GROUP BY \n" +
            "    reclamo.titulo, \n" +
            "    u.username \n" +
            "ORDER BY \n" +
            "    cantidad_respuestas DESC; \n", nativeQuery = true)
    public List<String[]> obtenerResumenRespuestasPorReclamoYUsuario();

}
