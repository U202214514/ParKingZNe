package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Empresa;


import java.util.List;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query(value="SELECT \n" +
            "    emp.id_empresa, \n" +
            "    emp.nombre_empresa, \n" +
            "    COUNT(*) AS total_reservas \n" +
            "FROM \n" +
            "    reserva r \n" +
            "JOIN \n" +
            "    estacionamiento e ON r.id_estacionamiento = e.id_estacionamiento \n" +
            "JOIN \n" +
            "    empresa emp ON e.id_empresa = emp.id_empresa \n" +
            "WHERE \n" +
            "    r.estado_reserva = 'completada' \n" +
            "GROUP BY \n" +
            "    emp.id_empresa, emp.nombre_empresa \n" +
            "ORDER BY \n" +
            "    total_reservas DESC \n" +
            "LIMIT 1;", nativeQuery = true)
    public List<String[]> empresaMasReservada();
}
