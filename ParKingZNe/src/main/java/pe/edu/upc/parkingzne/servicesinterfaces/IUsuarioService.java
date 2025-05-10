package pe.edu.upc.parkingzne.servicesinterfaces;


import pe.edu.upc.parkingzne.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario listID(int id);
    public void delete(int id_usuario);
    public void update(Usuario u);
    public List<Usuario> usuarioporrol();
    void insertar(Usuario u);
    public List<Object[]>usuariosConEdadentre18y40anos();

}
