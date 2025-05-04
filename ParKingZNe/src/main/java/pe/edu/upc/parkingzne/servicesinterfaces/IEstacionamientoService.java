package pe.edu.upc.parkingzne.servicesinterfaces;

import pe.edu.upc.parkingzne.entities.Estacionamiento;

import java.util.List;

public interface IEstacionamientoService {
    public List<Estacionamiento> list();
    public void insert(Estacionamiento e);
    public Estacionamiento listId(int id);
    public void update(Estacionamiento e);
    public void delete(int id);
    public List<String[]> recaudacionDiariaxEstacionamiento();
}
