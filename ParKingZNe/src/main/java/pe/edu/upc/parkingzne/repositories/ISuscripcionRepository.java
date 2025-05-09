package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Suscripcion;


import java.util.List;


@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, Integer> {

    @Query(value = "SELECT \n" +
            "    u.username, \n" +
            "    'Hola ' || u.nombre || ' ' || u.apellidos || \n" +
            "\t', tu suscripción se vencerá el ' || s.fecha_fin || \n" +
            "\t'. Te recordamos que puedes renovarla antes de esa fecha.' AS mensaje \n" +
            "FROM \n" +
            "    suscripcion s \n" +
            "JOIN \n" +
            "    Usuario u ON s.id_usuario = u.id_usuario \n" +
            "WHERE \n" +
            "    CURRENT_DATE = s.fecha_inicio", nativeQuery = true)
    public List<String[]> MensajePorSuscripcion();
}
