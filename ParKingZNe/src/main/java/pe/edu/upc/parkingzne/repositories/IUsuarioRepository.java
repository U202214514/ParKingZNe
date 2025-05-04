package pe.edu.upc.parkingzne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.parkingzne.entities.Usuario;


import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Usuario findOneByUsername(String username);
    @Query("select count(u.username) from Usuario u where u.username =:username")
    public int buscarUsername(@Param("username") String nombre);
    @Transactional
    @Modifying
    @Query(value = "insert into roles (rol, id_usuario) VALUES (:rol, :id_usuario)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("id_usuario") Long id_usuario);

    @Query("SELECT u FROM Usuario u INNER JOIN Rol r ON u.id_usuario = r.us.id_usuario")
    public List<Usuario> usuarioporrol();

    @Query(value = "SELECT u.id_usuario, u.nombre, u.apellidos, COUNT(r.id_reserva) AS total_reservas \n" +
            "FROM usuario u \n" +
            "JOIN reserva r ON u.id_usuario = r.id_usuario \n" +
            "WHERE r.estado_reserva = 'Completada' \n" +
            "GROUP BY u.id_usuario \n" +
            "ORDER BY total_reservas DESC \n" +
            "LIMIT 1; \n", nativeQuery = true)
    public List<String[]> ObtenerUsuarioMasActivo();
}
