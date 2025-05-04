package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.NotificacionDTO;
import pe.edu.upc.parkingzne.dtos.NotificacionPorUsuarioDTO;
import pe.edu.upc.parkingzne.entities.Notificacion;
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
    public List<NotificacionDTO> Listar(){
        logger.info("Listando todas las notificaciones emitidas en el sistema");
        return nS.list().stream().map(t->{
            ModelMapper m = new ModelMapper();
            return m.map(t, NotificacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public void insertar(@RequestBody NotificacionDTO dto){
        logger.info("Insertando nueva notificacion para el usuario con ID: {}", dto.getIdNotificacion());
        ModelMapper m = new ModelMapper();
        Notificacion n= m.map(dto, Notificacion.class);
        nS.insert(n);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public NotificacionDTO buscarId(@PathVariable("id") int id){
        logger.info("Buscando notificacion con ID: {}", id);
        ModelMapper m = new ModelMapper();
        NotificacionDTO dto = m.map(nS, NotificacionDTO.class);
        return dto;
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public void modificar(@RequestBody NotificacionDTO dto){
        logger.info("Modificando suscripcion con ID: {}", dto.getIdNotificacion());
        ModelMapper m = new ModelMapper();
        Notificacion n= m.map(dto, Notificacion.class);
        nS.update(n);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public void eliminar(@PathVariable("id") int id){
        logger.info("Eliminando notificacion con ID: {}", id);
        nS.delete(id);
    }

    @GetMapping("/notificaciones-usuarios")
    @PreAuthorize("hasAnyAuthority('ADNF')")
    public List<NotificacionPorUsuarioDTO> notificacionesPorUsuario(){
        logger.info("Generando notificaciones emitidas en el sistema");
        List<String[]> fila =nS.notificacionesxUsuario();
        List<NotificacionPorUsuarioDTO> dtoLista = new ArrayList<>();

        for(String[] columna : fila){
            NotificacionPorUsuarioDTO dto = new NotificacionPorUsuarioDTO();
            dto.setTipoDeNotificacion(columna[0]);
            dto.setMensaje(columna[1]);
            dto.setFechaEmision(LocalDate.parse(columna[2]));
            dto.setEstado(columna[3]);
            dto.setId_usuario(Integer.parseInt(columna[4]));
        }
        return dtoLista;
    }

}
