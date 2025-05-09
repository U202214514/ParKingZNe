package pe.edu.upc.parkingzne.servicesinterfaces;

import pe.edu.upc.parkingzne.entities.Reclamo;

import java.util.List;

public interface IReclamoService {
    public List<Reclamo> list();
    public void insert(Reclamo rm);
    public Reclamo listId(int id);
    public void update(Reclamo rm);
    public void delete(int id);
    public List<Object[]>ReporteReclamosUsuario();
}
