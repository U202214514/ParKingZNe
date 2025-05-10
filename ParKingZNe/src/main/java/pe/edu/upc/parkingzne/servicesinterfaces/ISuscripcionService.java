package pe.edu.upc.parkingzne.servicesinterfaces;

import pe.edu.upc.parkingzne.entities.Suscripcion;

import java.util.List;

public interface ISuscripcionService {
    public List<Suscripcion> list();
    public void insert(Suscripcion sp);
    public Suscripcion listId(int id);
    public void update(Suscripcion sp);
    public void delete(int id);
    public List<Object[]> MensajePorSuscripcion();

}
