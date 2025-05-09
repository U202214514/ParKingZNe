package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.ReservaDTO;
import pe.edu.upc.parkingzne.dtos.ReservaUsuarioDTO;
import pe.edu.upc.parkingzne.dtos.ReservasActivasUsuarioDTO;
import pe.edu.upc.parkingzne.entities.Reserva;
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
    public List<ReservaDTO> listar(){
        logger.info("Listando todas las reservas");
        return rS.list().stream().map( v->{
            ModelMapper m = new ModelMapper();
            return m.map(v, ReservaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void insertar(@RequestBody ReservaDTO dto) {
        logger.info("Insertando nueva reserva para usuario con ID: {}", dto.getIdReserva());
        ModelMapper m = new ModelMapper();
        Reserva reserva = m.map(dto, Reserva.class);
        rS.insert(reserva);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public ReservaDTO buscarId(@PathVariable("id")int id) {
        logger.info("Buscando reservas con ID: {}", id);
        ModelMapper m = new ModelMapper();
        ReservaDTO dto = m.map(rS, ReservaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void modificar(@RequestBody ReservaDTO dto) {
        logger.info("Modificando reserva con ID: {}", dto.getIdReserva());
        ModelMapper m = new ModelMapper();
        Reserva reserva = m.map(dto, Reserva.class);
        rS.update(reserva);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public void eliminar(@PathVariable("id")int id) {
        logger.info("Eliminando reserva realizada con ID: {}", id);
        rS.delete(id);
    }

    @GetMapping("/historialusers")
    @PreAuthorize("hasAnyAuthority('ADM')")
    public List<ReservaUsuarioDTO> historial(){
        logger.info("Generando historial de usuarios");
        List<String[]> fila =rS.listarReservasPorUsuario();
        List<ReservaUsuarioDTO> dtoLista = new ArrayList<>();

        for(String[] columna : fila){
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
    public List<ReservasActivasUsuarioDTO> reservasActivasUsuario(){
        logger.info("Generando reservas activas de los usuarios");
        List<Object[]> fila =rS.listarCantidadReservasActivasPorUsuario();
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
