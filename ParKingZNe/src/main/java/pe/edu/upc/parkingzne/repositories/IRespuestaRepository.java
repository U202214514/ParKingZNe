package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Respuesta;

import java.util.List;

@Repository
public interface IRespuestaRepository extends JpaRepository<Respuesta, Integer> {

    @Query(value = "SELECT " +
            "    COUNT(r.id_respuesta) AS cantidadRespuestas, " +
            "    reclamo.titulo AS tituloReclamo, " +
            "    u.username " +
            "FROM " +
            "    Respuesta r " +
            "JOIN " +
            "    Reclamo reclamo ON r.id_reclamo = reclamo.id_reclamo " +
            "JOIN " +
            "    Usuario u ON reclamo.id_usuario = u.id_usuario " +
            "GROUP BY " +
            "    reclamo.titulo, " +
            "    u.username " +
            "ORDER BY " +
            "    cantidadRespuestas DESC",
            nativeQuery = true)
    public List<Object[]> obtenerResumenRespuestasPorReclamoYUsuario();

}
