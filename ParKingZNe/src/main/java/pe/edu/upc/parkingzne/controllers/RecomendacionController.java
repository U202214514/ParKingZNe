package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.RecomendacionDTO;
import pe.edu.upc.parkingzne.entities.Recomendacion;
import pe.edu.upc.parkingzne.servicesinterfaces.IRecomendacionService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    private static final Logger logger = LoggerFactory.getLogger(RecomendacionController.class);
    @Autowired
    private IRecomendacionService cS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<Recomendacion> list(){
        logger.info("Listando todas las recomendaciones");
        return cS.list().stream().map(o->{
            ModelMapper m = new ModelMapper();
            return m.map(o,Recomendacion.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void insertar(@RequestBody RecomendacionDTO dto){
        logger.info("Insertando nuevos estacionamientos para recomendar a los usuarios con ID's: {}", dto.getIdRecomendacion());
        ModelMapper m = new ModelMapper();
        Recomendacion rr = m.map(dto, Recomendacion.class);
        cS.insert(rr);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public RecomendacionDTO buscarId(@PathVariable("id") int id){
        logger.info("Buscando Recomendaciones con ID: {}", id);
        ModelMapper m = new ModelMapper();
        RecomendacionDTO dto = m.map(cS, RecomendacionDTO.class);
        return dto;
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void modificar(@RequestBody RecomendacionDTO dto){
        logger.info("Modificando recomendacion segun las calificaciones con ID's: {}", dto.getIdRecomendacion());
        ModelMapper m = new ModelMapper();
        Recomendacion rr = m.map(dto, Recomendacion.class);
        cS.update(rr);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void eliminar(@PathVariable("id") int id){
        logger.info("Eliminando recomendaciones con ID: {}", id);
        cS.delete(id);
    }
}
