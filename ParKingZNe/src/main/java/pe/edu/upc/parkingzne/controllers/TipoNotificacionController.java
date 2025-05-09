package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.TipoNotificacionContadorDTO;
import pe.edu.upc.parkingzne.dtos.TipoNotificacionDTO;
import pe.edu.upc.parkingzne.entities.TipoNotificacion;
import pe.edu.upc.parkingzne.servicesinterfaces.ITipoNotificacionService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tiponotificaciones")
public class TipoNotificacionController {

    private static final Logger logger = LoggerFactory.getLogger(TipoNotificacionController.class);

    @Autowired
    private ITipoNotificacionService pS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public List<TipoNotificacionDTO> listar(){
        logger.info("Listando todos los tipos de notificaciones");
            return pS.list().stream().map(t->{
                ModelMapper m = new ModelMapper();
                return m.map(t, TipoNotificacionDTO.class);
            }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public void insertar(@RequestBody TipoNotificacionDTO dto){
        logger.info("Insertando nuevo tipo de notificacion para las notificaciones con ID: {}", dto.getIdTipoNotificacion());
        ModelMapper m = new ModelMapper();
        TipoNotificacion tp = m.map(dto, TipoNotificacion.class);
        pS.insert(tp);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public TipoNotificacionDTO buscarId(@PathVariable ("id")int id){
        logger.info("Buscando Tipo de notificacion con ID: {}", id);
        ModelMapper m = new ModelMapper();
        TipoNotificacionDTO dto = m.map(pS, TipoNotificacionDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public void modificar(@RequestBody TipoNotificacionDTO dto){
        logger.info("Modificando tipo de notificacion con ID: {}", dto.getIdTipoNotificacion());
        ModelMapper m = new ModelMapper();
        TipoNotificacion tp = m.map(dto, TipoNotificacion.class);
        pS.update(tp);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public void eliminar(@PathVariable("id") int id){
        logger.info("Eliminando tipo de notificacion con ID: {}", id);
        pS.delete(id);
    }


    @GetMapping("/cantidades-tiponotificaciones")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public List<TipoNotificacionContadorDTO> cantidadTiponotificaciones(){
        logger.info("Generando cantidad de tipo notificaciones");
        List<String[]> fila = pS.CantidadxtipoNotificacion();
        List<TipoNotificacionContadorDTO> dtoLista = new ArrayList<>();

        for(String[] columna : fila){
            TipoNotificacionContadorDTO dto = new TipoNotificacionContadorDTO();
            dto.setTipoNotificacion(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }
}
