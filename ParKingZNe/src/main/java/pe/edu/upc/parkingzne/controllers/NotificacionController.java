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
import pe.edu.upc.parkingzne.dtos.NotificacionDTO;
import pe.edu.upc.parkingzne.dtos.NotificacionPorUsuarioDTO;
import pe.edu.upc.parkingzne.entities.Notificacion;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.INotificacionService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private static final Logger logger = LoggerFactory.getLogger(NotificacionController.class);

    @Autowired
    private INotificacionService nS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public List<NotificacionDTO> listar() {
        logger.info("Listando todas las notificaciones emitidas en el sistema");
        return nS.list().stream().map(t -> {
            ModelMapper m = new ModelMapper();
            return m.map(t, NotificacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public ResponseEntity<String> insertar(@Valid @RequestBody NotificacionDTO dto) {
        logger.info("Insertando nueva notificación");

        if (dto.getMensaje() == null || dto.getMensaje().isBlank()) {
            throw new RequestBodyException("El mensaje de la notificación es obligatorio.");
        }

        ModelMapper m = new ModelMapper();
        Notificacion n = m.map(dto, Notificacion.class);
        nS.insert(n);

        return new ResponseEntity<>("Notificación registrada correctamente.", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public NotificacionDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando notificación con ID: {}", id);
        ModelMapper m = new ModelMapper();
        NotificacionDTO dto = m.map(nS.listId(id), NotificacionDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public ResponseEntity<String> modificar(@Valid @RequestBody NotificacionDTO dto) {
        logger.info("Modificando notificación con ID: {}", dto.getIdNotificacion());

        ModelMapper m = new ModelMapper();
        Notificacion n = m.map(dto, Notificacion.class);
        nS.update(n);

        return new ResponseEntity<>("Notificación modificada correctamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando notificación con ID: {}", id);
        nS.delete(id);
        return new ResponseEntity<>("Notificación eliminada correctamente.", HttpStatus.OK);
    }

    @GetMapping("/notificaciones-usuarios")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public List<NotificacionPorUsuarioDTO> notificacionesPorUsuario() {
        logger.info("Generando listado de notificaciones emitidas por usuario");

        List<String[]> fila = nS.notificacionesxUsuario();
        List<NotificacionPorUsuarioDTO> dtoLista = new ArrayList<>();

        for (String[] columna : fila) {
            NotificacionPorUsuarioDTO dto = new NotificacionPorUsuarioDTO();
            dto.setTipoDeNotificacion(columna[0]);
            dto.setMensaje(columna[1]);
            dto.setFechaEmision(LocalDate.parse(columna[2]));
            dto.setEstado(columna[3]);
            dto.setId_usuario(Integer.parseInt(columna[4]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

}
