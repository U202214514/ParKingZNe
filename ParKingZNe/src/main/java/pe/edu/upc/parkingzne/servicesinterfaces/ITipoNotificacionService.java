package pe.edu.upc.parkingzne.servicesinterfaces;

import pe.edu.upc.parkingzne.entities.TipoNotificacion;

import java.util.List;

public interface ITipoNotificacionService {
    public List<TipoNotificacion> list();
    public void insert(TipoNotificacion tp);
    public TipoNotificacion listId(int id);
    public void update(TipoNotificacion tp);
    public void delete(int id);
    public List<String[]> CantidadxtipoNotificacion();
}
