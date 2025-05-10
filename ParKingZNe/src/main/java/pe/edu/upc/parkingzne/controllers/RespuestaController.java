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
import pe.edu.upc.parkingzne.dtos.RespuestaDTO;
import pe.edu.upc.parkingzne.dtos.RespuestasxReclamoDTO;
import pe.edu.upc.parkingzne.entities.Respuesta;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IRespuestaService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private static final Logger logger = LoggerFactory.getLogger(RespuestaController.class);

    @Autowired
    private IRespuestaService eS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public List<RespuestaDTO> list() {
        logger.info("Listando todas las respuestas realizadas por los administradores del área de Reclamos");
        return eS.list().stream().map(w -> {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(w, RespuestaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public ResponseEntity<String> insertar(@Valid @RequestBody RespuestaDTO dto) {
        logger.info("Insertando nueva respuesta para el reclamo con ID: {}", dto.getIdRespuesta());

        if (dto.getFechaRespuesta() == null ) {
            throw new RequestBodyException("La fecha de la respuesta no puede estar vacío.");
        }

        ModelMapper modelMapper = new ModelMapper();
        Respuesta ra = modelMapper.map(dto, Respuesta.class);
        eS.insert(ra);

        return ResponseEntity.status(HttpStatus.CREATED).body("Respuesta registrada correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public RespuestaDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando respuesta con ID: {}", id);
        ModelMapper modelMapper = new ModelMapper();
        RespuestaDTO dto = modelMapper.map(eS.listId(id), RespuestaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public ResponseEntity<String> modificar(@Valid @RequestBody RespuestaDTO dto) {
        logger.info("Modificando respuesta con ID: {}", dto.getIdRespuesta());

        ModelMapper modelMapper = new ModelMapper();
        Respuesta ra = modelMapper.map(dto, Respuesta.class);
        eS.update(ra);

        return ResponseEntity.ok("Respuesta modificada correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando respuesta con ID: {}", id);
        eS.delete(id);
        return ResponseEntity.ok("Respuesta eliminada correctamente.");
    }

    @GetMapping("/cantidadrespuestas-reclamos")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public List<RespuestasxReclamoDTO> cantidadRespuestasReclamos() {
        logger.info("Generando resumen de respuestas por reclamo y usuario");

        List<Object[]> fila = eS.obtenerResumenRespuestasPorReclamoYUsuario();
        List<RespuestasxReclamoDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            RespuestasxReclamoDTO dto = new RespuestasxReclamoDTO();
            dto.setCantidadRespuestas(((Number) columna[0]).intValue());
            dto.setTitulo_reclamo((String) columna[1]);
            dto.setUsername((String) columna[2]);
            dtoLista.add(dto);
        }
        return dtoLista;
    }
}
