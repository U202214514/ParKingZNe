package pe.edu.upc.parkingzne.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.parkingzne.entities.NotificacionReserva;
import pe.edu.upc.parkingzne.repositories.INotificacionReservaRepository;
import pe.edu.upc.parkingzne.servicesinterfaces.INotificacionReservaService;

import java.util.List;

@Service
public class NotificacionReservaServiceImplement implements INotificacionReservaService {

    @Autowired
    private INotificacionReservaRepository nR;

    @Override
    public List<NotificacionReserva> list() {
        return nR.findAll();
    }

    @Override
    public void insert(NotificacionReserva notificacionReserva) {
        nR.save(notificacionReserva);
    }

    @Override
    public NotificacionReserva listId(int id) {
        return nR.findById(id).orElse(new NotificacionReserva());
    }

    @Override
    public void update(NotificacionReserva notificacionReserva) {
        nR.save(notificacionReserva);
    }

    @Override
    public void delete(int id) {
        nR.deleteById(id);
    }

    @Override
    public List<Object[]> CantidaddeNotificionesPorReservas() {
        return nR.CantidaddeNotificionesPorReservas();
    }
}
