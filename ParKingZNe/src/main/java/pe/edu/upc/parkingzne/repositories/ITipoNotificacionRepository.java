package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.TipoNotificacion;


import java.util.List;

@Repository
public interface ITipoNotificacionRepository extends JpaRepository<TipoNotificacion, Integer> {

    @Query(value = "SELECT \n" +
            "    tn.descripcion AS tipoNotificacion, \n" +
            "    COUNT(n.id_notificacion) AS cantidad\n" +
            "FROM \n" +
            "    notificacion n\n" +
            "JOIN \n" +
            "    tipo_notificacion tn ON n.id_tipo_notificacion = tn.id_tipo_notificacion\n" +
            "GROUP BY \n" +
            "    tn.descripcion;", nativeQuery = true)
    public List<String[]> CantidadxtipoNotificacion();
}
