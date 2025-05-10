package pe.edu.upc.parkingzne.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.parkingzne.entities.Usuario;
import pe.edu.upc.parkingzne.repositories.IUsuarioRepository;
import pe.edu.upc.parkingzne.servicesinterfaces.IUsuarioService;


import java.util.List;

@Lazy
@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uR;

    @Override
    public List<Usuario> listarUsuarios() {
        return uR.findAll();
    }

    @Override
    public Usuario listID(int id) {
        return uR.findById(id).orElse(new Usuario() );
    }

    @Override
    public void delete(int id_usuario) { uR.deleteById(id_usuario); }
    @Override
    public void update(Usuario u) { uR.save(u); }

    @Override
    public List<Usuario> usuarioporrol() {
        return uR.usuarioporrol();
    }

    @Override
    public void insertar(Usuario u) {
        uR.save(u);
    }

    @Override
    public List<Object[]> usuariosConEdadentre18y40anos() {
        return uR.usuariosConEdadentre18y40anos();
    }


}
