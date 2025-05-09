package pe.edu.upc.parkingzne.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.parkingzne.entities.Reserva;
import pe.edu.upc.parkingzne.repositories.IReservaRepository;
import pe.edu.upc.parkingzne.servicesinterfaces.IReservaService;

import java.util.List;

@Service
public class ReservaServiceImplement implements IReservaService {

    @Autowired
    private IReservaRepository rR;
    @Override
    public List<Reserva> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Reserva reserva) {
        rR.save(reserva);
    }

    @Override
    public Reserva listId(int id) {
        return rR.findById(id).orElse(new Reserva());
    }

    @Override
    public void update(Reserva reserva) {
        rR.save(reserva);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public List<String[]> listarReservasPorUsuario() {
        return rR.listarReservasPorUsuario();
    }

    @Override
    public List<Object[]> listarCantidadReservasActivasPorUsuario() {
        return rR.listarCantidadReservasActivasPorUsuario();
    }


}
