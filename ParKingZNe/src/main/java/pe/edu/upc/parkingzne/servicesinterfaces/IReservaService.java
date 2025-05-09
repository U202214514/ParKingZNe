package pe.edu.upc.parkingzne.servicesinterfaces;

import pe.edu.upc.parkingzne.entities.Reserva;

import java.util.List;

public interface IReservaService {

    public List<Reserva> list();
    public void insert(Reserva reserva);
    public Reserva listId(int id);
    public void update(Reserva reserva);
    public void delete(int id);
    public List<String[]> listarReservasPorUsuario();
    public List<Object[]> listarCantidadReservasActivasPorUsuario();
}
