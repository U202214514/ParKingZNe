package pe.edu.upc.parkingzne.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.RecomendacionDTO;
import pe.edu.upc.parkingzne.dtos.RecomendacionPorEstacionamientoDTO;
import pe.edu.upc.parkingzne.entities.Recomendacion;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IRecomendacionService;


import java.util.ArrayList;
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
    public List<RecomendacionDTO> list() {
        logger.info("Listando todas las recomendaciones");
        return cS.list().stream().map(o -> {
            ModelMapper m = new ModelMapper();
            return m.map(o, RecomendacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> insertar(@Valid @RequestBody RecomendacionDTO dto) {
        logger.info("Insertando recomendación de estacionamiento");

        if (dto.getComentario() == null || dto.getComentario().isBlank()) {
            throw new RequestBodyException("El comentario de la recomendación no puede estar vacío.");
        }

        ModelMapper m = new ModelMapper();
        Recomendacion rr = m.map(dto, Recomendacion.class);
        cS.insert(rr);

        return ResponseEntity.status(HttpStatus.CREATED).body("Recomendación registrada correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public RecomendacionDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando recomendación con ID: {}", id);
        ModelMapper m = new ModelMapper();
        RecomendacionDTO dto = m.map(cS.listId(id), RecomendacionDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> modificar(@Valid @RequestBody RecomendacionDTO dto) {
        logger.info("Modificando recomendación con ID: {}", dto.getIdRecomendacion());
        ModelMapper m = new ModelMapper();
        Recomendacion rr = m.map(dto, Recomendacion.class);
        cS.update(rr);
        return ResponseEntity.ok("Recomendación modificada correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando recomendación con ID: {}", id);
        cS.delete(id);
        return ResponseEntity.ok("Recomendación eliminada correctamente.");
    }

    @GetMapping("/cantidad-calificaciones-estacionamientos")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<RecomendacionPorEstacionamientoDTO> cantidadRecomendacionesEstacionamientos() {
        logger.info("Listando recomendaciones agrupadas por estacionamiento");

        List<Object[]> fila = cS.cantidadRecomendacionesEstacionamientos();
        List<RecomendacionPorEstacionamientoDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            RecomendacionPorEstacionamientoDTO dto = new RecomendacionPorEstacionamientoDTO();
            dto.setEstacionamiento_Recomendado((String) columna[0]);
            dto.setCantidad_calificaciones(((Number) columna[1]).intValue());
            dtoLista.add(dto);
        }

        return dtoLista;
    }

}
