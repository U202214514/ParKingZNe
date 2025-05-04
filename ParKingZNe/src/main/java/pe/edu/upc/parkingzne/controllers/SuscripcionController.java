package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.MensajeSuscripcionDTO;
import pe.edu.upc.parkingzne.dtos.SuscripcionDTO;
import pe.edu.upc.parkingzne.entities.Suscripcion;
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
    public List<SuscripcionDTO> list(){
        logger.info("Listando todas las suscripciones");
        return sR.list().stream().map(H->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(H, SuscripcionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public void insertar(@RequestBody SuscripcionDTO dto) {
        logger.info("Insertando nueva suscripción para el usuario con ID: {}", dto.getUs());
        ModelMapper m = new ModelMapper();
        Suscripcion sp=m.map(dto, Suscripcion.class);
        sR.insert(sp);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public SuscripcionDTO buscarId(@PathVariable("id") int id) {
        logger.info("Buscando suscripción con ID: {}", id);
        ModelMapper m = new ModelMapper();
        SuscripcionDTO dto = m.map(sR, SuscripcionDTO.class);
        return dto;
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public void modificar(@RequestBody SuscripcionDTO dto) {
        logger.info("Modificando suscripción con ID: {}", dto.getIdSuscripcion());
        ModelMapper m = new ModelMapper();
        Suscripcion sp=m.map(dto, Suscripcion.class);
        sR.update(sp);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public void eliminar(@PathVariable("id") int id) {
        logger.info("Eliminando suscripción con ID: {}", id);
        sR.delete(id);
    }

    @GetMapping("/mensaje-suscripcion-vencera-usario")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public List<MensajeSuscripcionDTO> mensajeSuscripcion() {
        logger.info("Generando mensajes para suscripciones activas hoy");
        List<String[]> fila = sR.MensajePorSuscripcion();
        List<MensajeSuscripcionDTO> dtoLista = new ArrayList<>();

        for(String[] columna: fila){
            MensajeSuscripcionDTO dto = new MensajeSuscripcionDTO();
            dto.setUsername(columna[0]);
            dto.setMensaje(columna[1]);
        }
        return dtoLista;
    }
}
