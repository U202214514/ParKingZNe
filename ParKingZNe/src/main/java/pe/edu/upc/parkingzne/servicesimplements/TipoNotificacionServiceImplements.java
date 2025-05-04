package pe.edu.upc.parkingzne.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.parkingzne.entities.TipoNotificacion;
import pe.edu.upc.parkingzne.repositories.ITipoNotificacionRepository;
import pe.edu.upc.parkingzne.servicesinterfaces.ITipoNotificacionService;


import java.util.List;

@Service
public class TipoNotificacionServiceImplements implements ITipoNotificacionService {
    @Autowired
    private ITipoNotificacionRepository pR;

    @Override
    public List<TipoNotificacion> list() {
        return pR.findAll();
    }

    @Override
    public void insert(TipoNotificacion tp) {
        pR.save(tp);
    }

    @Override
    public TipoNotificacion listId(int id) {
        return pR.findById(id).orElse(new TipoNotificacion());
    }

    @Override
    public void update(TipoNotificacion tp) {
        pR.save(tp);
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }

    @Override
    public List<String[]> CantidadxtipoNotificacion() {
        return pR.CantidadxtipoNotificacion();
    }

}
