package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.NotificacionReservaDTO;
import pe.edu.upc.parkingzne.entities.NotificacionReserva;
import pe.edu.upc.parkingzne.servicesinterfaces.INotificacionReservaService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificacionesreservadas")
public class NotificacionReservaController {

    private static final Logger logger = LoggerFactory.getLogger(NotificacionReservaController.class);

    @Autowired
    private INotificacionReservaService nS;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<NotificacionReservaDTO> listar(){
        logger.info("Listando todas la notificaciones por reserva que estan en el sistema");
        return nS.list().stream().map(n->{
            ModelMapper m = new ModelMapper();
            return m.map(n, NotificacionReservaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void insertar(@RequestBody NotificacionReservaDTO dto) {
        logger.info("Insertando nuevas notificaciones por reserva con ID's: {}", dto.getIdNotificacionReserva());
        ModelMapper m = new ModelMapper();
        NotificacionReserva notificacionReserva = m.map(dto, NotificacionReserva.class);
        nS.insert(notificacionReserva);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public NotificacionReservaDTO buscarId(@PathVariable("id")int id ){
        logger.info("Buscando notificacion por reserva con ID: {}", id);
        ModelMapper m = new ModelMapper();
        NotificacionReservaDTO dto = m.map(nS, NotificacionReservaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void modificar(@RequestBody NotificacionReservaDTO dto) {
        logger.info("Modificando Notificacion por reserva con ID: {}", dto.getIdNotificacionReserva());
        ModelMapper m = new ModelMapper();
        NotificacionReserva notificacionReserva = m.map(dto, NotificacionReserva.class);
        nS.update(notificacionReserva);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void eliminar(@PathVariable("id")int id) {
        logger.info("Eliminando notificacion por reserva con ID: {}", id);
        nS.delete(id);
    }
}
