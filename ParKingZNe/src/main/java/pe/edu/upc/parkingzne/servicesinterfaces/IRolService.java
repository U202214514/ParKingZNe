package pe.edu.upc.parkingzne.servicesinterfaces;

import pe.edu.upc.parkingzne.entities.Rol;

import java.util.List;

public interface IRolService {
    public List<Rol> list();
    public void insert(Rol r);
    public Rol listId(int id);
    public void update(Rol r);
    public void delete(int id);
    public List<Object[]> CantidadRolesxTipo();
}
