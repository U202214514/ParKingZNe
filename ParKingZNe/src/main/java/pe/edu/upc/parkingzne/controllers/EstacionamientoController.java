package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.EstacionamientoDTO;
import pe.edu.upc.parkingzne.dtos.MontoDiarioEstacionamientoDTO;
import pe.edu.upc.parkingzne.entities.Estacionamiento;
import pe.edu.upc.parkingzne.servicesinterfaces.IEstacionamientoService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estacionamientos")
public class EstacionamientoController {

    private static final Logger logger = LoggerFactory.getLogger(EstacionamientoController.class);
    @Autowired
    private IEstacionamientoService aS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public List<Estacionamiento> listar(){
        logger.info("Listando estacionamientos");
        return aS.list().stream().map(i->{
            ModelMapper m = new ModelMapper();
            return m.map(i,Estacionamiento.class);
        }).collect(Collectors.toList());

    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public void insertar(@RequestBody EstacionamientoDTO dto){
        logger.info("Insertando nuevo estacionamiento para el usuario con ID: {}", dto.getIdEstacionamiento());
        ModelMapper m = new ModelMapper();
        Estacionamiento e = m.map(dto, Estacionamiento.class);
        aS.insert(e);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public EstacionamientoDTO buscarId(@PathVariable("id") int id){
        logger.info("Buscando estacionamientos con ID: {}", id);
        ModelMapper m = new ModelMapper();
        EstacionamientoDTO dto = m.map(aS, EstacionamientoDTO.class);
        return dto;
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public void modificar(@RequestBody EstacionamientoDTO dto){
        logger.info("Modificando estacionamientos con ID: {}", dto.getIdEstacionamiento());
        ModelMapper m = new ModelMapper();
        Estacionamiento e = m.map(dto, Estacionamiento.class);
        aS.update(e);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public void eliminar(@PathVariable("id") int id){
        logger.info("Eliminando estacionamientos con ID: {}", id);
        aS.delete(id);
    }


    @GetMapping("/monto-diario-estacionamientos")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public List<MontoDiarioEstacionamientoDTO> montoDiarioEstacionamiento(){
        List<String[]> fila = aS.recaudacionDiariaxEstacionamiento();
        List<MontoDiarioEstacionamientoDTO> dtoLista = new ArrayList<>();

        for(String[] columna : fila){
            MontoDiarioEstacionamientoDTO dto = new MontoDiarioEstacionamientoDTO();
            dto.setIdEstacionamiento(Integer.parseInt(columna[0]));
            dto.setNombreEstacionamiento(columna[1]);
            dto.setFechaReserva(LocalDate.parse(columna[2]));
            dto.setCantidadReservas(Integer.parseInt(columna[3]));
            dto.setTotalGanado(Double.parseDouble(columna[4]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }
}
