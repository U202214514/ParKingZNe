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
import pe.edu.upc.parkingzne.dtos.ReservaDTO;
import pe.edu.upc.parkingzne.dtos.ReservaUsuarioDTO;
import pe.edu.upc.parkingzne.dtos.ReservasActivasUsuarioDTO;
import pe.edu.upc.parkingzne.entities.Reserva;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IReservaService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);

    @Autowired
    private IReservaService rS;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<ReservaDTO> listar() {
        logger.info("Listando todas las reservas");
        return rS.list().stream().map(v -> {
            ModelMapper m = new ModelMapper();
            return m.map(v, ReservaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> insertar(@Valid @RequestBody ReservaDTO dto) {
        logger.info("Insertando nueva reserva");

        if (dto.getFechaReserva() == null || dto.getEstadoReserva() == null || dto.getEstadoReserva().isBlank()) {
            throw new RequestBodyException("Los campos 'fechaReserva' y 'estadoReserva' son obligatorios.");
        }

        ModelMapper m = new ModelMapper();
        Reserva reserva = m.map(dto, Reserva.class);
        rS.insert(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body("Reserva registrada correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ReservaDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando reserva con ID: {}", id);
        ModelMapper m = new ModelMapper();
        ReservaDTO dto = m.map(rS.listId(id), ReservaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> modificar(@Valid @RequestBody ReservaDTO dto) {
        logger.info("Modificando reserva con ID: {}", dto.getIdReserva());
        ModelMapper m = new ModelMapper();
        Reserva reserva = m.map(dto, Reserva.class);
        rS.update(reserva);
        return ResponseEntity.ok("Reserva modificada correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando reserva con ID: {}", id);
        rS.delete(id);
        return ResponseEntity.ok("Reserva eliminada correctamente.");
    }

    @GetMapping("/historialusers")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<ReservaUsuarioDTO> historial() {
        logger.info("Generando historial de usuarios");
        List<String[]> fila = rS.listarReservasPorUsuario();
        List<ReservaUsuarioDTO> dtoLista = new ArrayList<>();

        for (String[] columna : fila) {
            ReservaUsuarioDTO dto = new ReservaUsuarioDTO();
            dto.setUsername(columna[0]);
            dto.setApellidos(columna[1]);
            dto.setFechaReserva(LocalDate.parse(columna[2]));
            dto.setEstadoReserva(columna[3]);
            dtoLista.add(dto);
        }

        return dtoLista;
    }

    @GetMapping("/reservas-activas-usuario")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<ReservasActivasUsuarioDTO> reservasActivasUsuario() {
        logger.info("Generando reservas activas de los usuarios");
        List<Object[]> fila = rS.listarCantidadReservasActivasPorUsuario();
        List<ReservasActivasUsuarioDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            ReservasActivasUsuarioDTO dto = new ReservasActivasUsuarioDTO();
            dto.setUsername((String) columna[0]);
            dto.setApellidos((String) columna[1]);
            dto.setCantidadReservasActivas(((Number) columna[2]).intValue());
            dtoLista.add(dto);
        }
        return dtoLista;
    }
}
