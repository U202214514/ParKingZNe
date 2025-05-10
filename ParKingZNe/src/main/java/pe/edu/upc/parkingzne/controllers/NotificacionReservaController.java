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
import pe.edu.upc.parkingzne.dtos.NotificacionReservaDTO;
import pe.edu.upc.parkingzne.dtos.TotalReservasNotificadasDTO;
import pe.edu.upc.parkingzne.entities.NotificacionReserva;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.INotificacionReservaService;


import java.util.ArrayList;
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
    public List<NotificacionReservaDTO> listar() {
        logger.info("Listando todas las notificaciones por reserva");
        return nS.list().stream().map(n -> {
            ModelMapper m = new ModelMapper();
            return m.map(n, NotificacionReservaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> insertar(@Valid @RequestBody NotificacionReservaDTO dto) {
        logger.info("Insertando notificación por reserva");

        if (dto.getReserva() == null ) {
            throw new RequestBodyException("El id de la reserva es obligatorio.");
        }

        ModelMapper m = new ModelMapper();
        NotificacionReserva notificacionReserva = m.map(dto, NotificacionReserva.class);
        nS.insert(notificacionReserva);

        return ResponseEntity.status(HttpStatus.CREATED).body("Notificación por reserva registrada correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public NotificacionReservaDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando notificación por reserva con ID: {}", id);
        ModelMapper m = new ModelMapper();
        NotificacionReservaDTO dto = m.map(nS.listId(id), NotificacionReservaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> modificar(@Valid @RequestBody NotificacionReservaDTO dto) {
        logger.info("Modificando notificación por reserva con ID: {}", dto.getIdNotificacionReserva());

        ModelMapper m = new ModelMapper();
        NotificacionReserva notificacionReserva = m.map(dto, NotificacionReserva.class);
        nS.update(notificacionReserva);

        return new ResponseEntity<>("Notificación modificada correctamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando notificación por reserva con ID: {}", id);
        nS.delete(id);
        return new ResponseEntity<>("Notificación eliminada correctamente.", HttpStatus.OK);
    }

    @GetMapping("/total-reservas-registradas")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<TotalReservasNotificadasDTO> cantidadDeReservasRegistradas() {
        logger.info("Listando cantidad de reservas notificadas");
        List<Object[]> fila = nS.CantidaddeNotificionesPorReservas();
        List<TotalReservasNotificadasDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            TotalReservasNotificadasDTO dto = new TotalReservasNotificadasDTO();
            dto.setTIPOS_RESERVA((String) columna[0]);
            dto.setCANTIDAD(((Number) columna[1]).intValue());
            dtoLista.add(dto);
        }
        return dtoLista;
    }
}
