package pe.edu.upc.parkingzne.servicesinterfaces;

import pe.edu.upc.parkingzne.entities.Pago;

import java.util.List;

public interface IPagoService {
    public List<Pago> list();
    public void insert(Pago pg);
    public Pago listId(int id);
    public void update(Pago pg);
    public void delete(int id);
    public List<Object[]> cantidadPagosxUsuario();

}
