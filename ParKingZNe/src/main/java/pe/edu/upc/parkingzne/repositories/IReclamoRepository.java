package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Reclamo;


import java.util.List;

@Repository
public interface IReclamoRepository extends JpaRepository<Reclamo, Integer> {

    @Query(value="SELECT \n" +
            "    u.id_usuario,\n" +
            "    u.username,\n" +
            "    r.fecha AS fecha_reclamo,\n" +
            "    COUNT(r.id_reclamo) AS cantidad_reclamos,\n" +
            "    SUM(CASE WHEN r.id_reserva IS NOT NULL THEN 1 ELSE 0 END) AS reclamos_con_reserva\n" +
            "FROM \n" +
            "    Usuario u\n" +
            "LEFT JOIN \n" +
            "    Reclamo r ON u.id_usuario = r.id_usuario\n" +
            "GROUP BY \n" +
            "    u.id_usuario, u.username, r.fecha\n" +
            "ORDER BY \n" +
            "    r.fecha DESC;",nativeQuery = true)
    public List<String[]>ReporteReclamosUsuario();
}
