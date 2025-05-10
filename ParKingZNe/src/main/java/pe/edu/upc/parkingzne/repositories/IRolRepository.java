package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Rol;

import java.util.List;


@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    @Query(value = "SELECT tipo_rol AS nombre_del_rol, COUNT(*) AS cantidad \n" +
            "FROM rol \n" +
            "GROUP BY tipo_rol;", nativeQuery = true)
    public List<Object[]> CantidadRolesxTipo();
}
