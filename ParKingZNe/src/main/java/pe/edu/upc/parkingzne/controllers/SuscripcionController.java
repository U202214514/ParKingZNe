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
import pe.edu.upc.parkingzne.dtos.MensajeSuscripcionDTO;
import pe.edu.upc.parkingzne.dtos.SuscripcionDTO;
import pe.edu.upc.parkingzne.entities.Suscripcion;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.ISuscripcionService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suscripciones")
public class SuscripcionController {

    private static final Logger logger = LoggerFactory.getLogger(SuscripcionController.class);
    @Autowired
    private ISuscripcionService sR;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public List<SuscripcionDTO> list() {
        logger.info("Listando todas las suscripciones");
        return sR.list().stream().map(H -> {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(H, SuscripcionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public ResponseEntity<String> insertar(@Valid @RequestBody SuscripcionDTO dto) {
        logger.info("Insertando nueva suscripción para el usuario");

        if (dto.getTipoSuscripcion() == null || dto.getTipoSuscripcion().isBlank()) {
            throw new RequestBodyException("El tipo de suscripción no puede estar vacío.");
        }

        ModelMapper m = new ModelMapper();
        Suscripcion sp = m.map(dto, Suscripcion.class);
        sR.insert(sp);

        return ResponseEntity.status(HttpStatus.CREATED).body("Suscripción registrada correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public SuscripcionDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando suscripción con ID: {}", id);
        ModelMapper m = new ModelMapper();
        SuscripcionDTO dto = m.map(sR.listId(id), SuscripcionDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public ResponseEntity<String> modificar(@Valid @RequestBody SuscripcionDTO dto) {
        logger.info("Modificando suscripción con ID: {}", dto.getIdSuscripcion());

        ModelMapper m = new ModelMapper();
        Suscripcion sp = m.map(dto, Suscripcion.class);
        sR.update(sp);

        return ResponseEntity.ok("Suscripción modificada correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando suscripción con ID: {}", id);
        sR.delete(id);
        return ResponseEntity.ok("Suscripción eliminada correctamente.");
    }

    @GetMapping("/mensaje-suscripcion-vencera-usario")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public List<MensajeSuscripcionDTO> mensajeSuscripcion() {
        logger.info("Generando mensajes de advertencia por vencimiento de suscripción");

        List<Object[]> fila = sR.MensajePorSuscripcion();
        List<MensajeSuscripcionDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            MensajeSuscripcionDTO dto = new MensajeSuscripcionDTO();
            dto.setUsername((String) columna[0]);
            dto.setMensaje((String) columna[1]);
            dtoLista.add(dto);
        }

        return dtoLista;
    }
}
