package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Notificacion;


import java.util.List;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {

    @Query(value = "SELECT \n" +
            "    tn.descripcion AS tipoDeNotificacion, \n" +
            "    n.mensaje, \n" +
            "    n.fecha_emision, \n" +
            "    n.estado, \n" +
            "    u.id_usuario AS id_usuario \n" +
            "FROM \n" +
            "    notificacion n\n" +
            "JOIN \n" +
            "    tipo_notificacion tn ON n.id_tipo_notificacion = tn.id_tipo_notificacion \n" +
            "JOIN \n" +
            "    usuario u ON n.id_usuario = u.id_usuario \n" +
            "ORDER BY \n" +
            "    n.fecha_emision DESC;", nativeQuery = true)
    public List<String[]>notificacionesxUsuario();
}
