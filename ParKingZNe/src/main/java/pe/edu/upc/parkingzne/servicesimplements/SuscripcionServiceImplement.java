package pe.edu.upc.parkingzne.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.parkingzne.entities.Suscripcion;
import pe.edu.upc.parkingzne.repositories.ISuscripcionRepository;
import pe.edu.upc.parkingzne.servicesinterfaces.ISuscripcionService;

import java.util.List;

@Service
public class SuscripcionServiceImplement implements ISuscripcionService {
    @Autowired
    private ISuscripcionRepository pR;
    @Override
    public List<Suscripcion> list() {
        return pR.findAll();
    }

    @Override
    public void insert(Suscripcion sp) {
        pR.save(sp);
    }

    @Override
    public Suscripcion listId(int id) {
        return pR.findById(id).orElse(new Suscripcion());
    }

    @Override
    public void update(Suscripcion sp) {
        pR.save(sp);
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }

    @Override
    public List<Object[]> MensajePorSuscripcion() {
        return pR.MensajePorSuscripcion();
    }
}
