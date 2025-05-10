package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Pago;


import java.util.List;

@Repository
public interface IPagoRepository extends JpaRepository<Pago, Integer> {

    @Query(value = "SELECT \n" +
            "    u.id_usuario AS ID, \n" +
            "\tu.nombre AS Nombre, \n" +
            "\tu.apellidos AS Apellidos, \n" +
            "    COUNT(p.id_pago) AS cantidad_pagos \n" +
            "\t \n" +
            "FROM \n" +
            "    pago p \n" +
            "JOIN \n" +
            "    suscripcion s ON p.id_suscripcion = s.id_suscripcion \n" +
            "JOIN \n" +
            "    usuario u ON s.id_usuario = u.id_usuario \n" +
            "WHERE \n" +
            "    p.fecha_pago BETWEEN '2020-07-13' AND '2026-12-12' \n" +
            "GROUP BY \n" +
            "    u.id_usuario, u.nombre, u.apellidos;", nativeQuery = true)
    public List<Object[]> cantidadPagosxUsuario();
}
