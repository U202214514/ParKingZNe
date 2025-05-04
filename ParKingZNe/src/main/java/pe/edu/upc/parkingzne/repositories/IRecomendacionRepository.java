package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.parkingzne.entities.Recomendacion;


@Repository
public interface IRecomendacionRepository extends JpaRepository<Recomendacion, Integer> {
}
