package pe.edu.upc.parkingzne.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.parkingzne.entities.Pago;
import pe.edu.upc.parkingzne.repositories.IPagoRepository;
import pe.edu.upc.parkingzne.servicesinterfaces.IPagoService;

import java.util.List;

@Service
public class PagoServiceImplement implements IPagoService {
    @Autowired
    private IPagoRepository gR;
    @Override
    public List<Pago> list() {
        return gR.findAll();
    }

    @Override
    public void insert(Pago pg) {
        gR.save(pg);
    }

    @Override
    public Pago listId(int id) {
        return gR.findById(id).orElse(new Pago());
    }

    @Override
    public void update(Pago pg) {
        gR.save(pg);
    }

    @Override
    public void delete(int id) {
        gR.deleteById(id);
    }

    @Override
    public List<Object[]> cantidadPagosxUsuario() {
        return gR.cantidadPagosxUsuario();
    }

}
