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

    @Query(value="SELECT DISTINCT u.id_usuario AS IDUser, \n" +
            "       u.username AS USERNAME,\n" +
            "       u.fechanacimiento AS FECHA,\n" +
            "       u.correo AS CORREO\n" +
            "FROM usuario u\n" +
            "JOIN rol r ON u.id_usuario = r.id_usuario\n" +
            "WHERE u.enabled = true\n" +
            "  AND u.fechanacimiento BETWEEN (CURRENT_DATE - INTERVAL '40 years') AND (CURRENT_DATE - INTERVAL '18 years')\n" +
            "  AND r.tipo_rol = 'SUBPREMIUN'\n" +
            "ORDER BY u.fechanacimiento DESC;", nativeQuery = true)
    public List<Object[]>usuariosConEdadentre18y40anos();
}
