package pe.edu.upc.parkingzne.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.parkingzne.entities.Rol;
import pe.edu.upc.parkingzne.repositories.IRolRepository;
import pe.edu.upc.parkingzne.servicesinterfaces.IRolService;

import java.util.List;

@Service
public class RolImplement implements IRolService {
    @Autowired
    private IRolRepository rR;

    @Override
    public List<Rol> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Rol r) {
        rR.save(r);
    }

    @Override
    public Rol listId(int id) {
        return rR.findById(id).orElse(new Rol());
    }

    @Override
    public void update(Rol r) {
        rR.save(r);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public List<Object[]> CantidadRolesxTipo() {
        return rR.CantidadRolesxTipo();
    }
}
