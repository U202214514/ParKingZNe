package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.RespuestaDTO;
import pe.edu.upc.parkingzne.dtos.RespuestasxReclamoDTO;
import pe.edu.upc.parkingzne.entities.Respuesta;
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
    public List<RespuestaDTO> list(){
        logger.info("Listando todas las respuestas realizadas por los administradores del area Reclamos");
        return eS.list().stream().map(w->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(w,RespuestaDTO.class);
        }).collect(Collectors.toList());

    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public void insertar(@RequestBody RespuestaDTO dto){
        logger.info("Insertando nueva respuesta para el reclamo con ID: {}", dto.getIdRespuesta());
        ModelMapper modelMapper = new ModelMapper();
        Respuesta ra = modelMapper.map(dto,Respuesta.class);
        eS.insert(ra);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public RespuestaDTO buscarId(@PathVariable("id") int id){
        logger.info("Buscando respuestas con ID: {}", id);
        ModelMapper modelMapper = new ModelMapper();
        RespuestaDTO dto = modelMapper.map(eS, RespuestaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public void modificar(@RequestBody RespuestaDTO dto){
        logger.info("Modificando respuesta para detallarla mejor con ID: {}", dto.getIdRespuesta());
        ModelMapper modelMapper = new ModelMapper();
        Respuesta ra = modelMapper.map(dto,Respuesta.class);
        eS.update(ra);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public void eliminar(@PathVariable("id") int id){
        logger.info("Eliminando respusta que ya fue enviada con ID: {}", id);
        eS.delete(id);
    }


    @GetMapping("/cantidadrespuestas-reclamos")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public List<RespuestasxReclamoDTO> cantidadRespuestasReclamos(){
        logger.info("Generando la cantidad de respuesta emitidas por reclamo y usuario");
        List<String[]> fila = eS.obtenerResumenRespuestasPorReclamoYUsuario();
        List<RespuestasxReclamoDTO> dtoLista = new ArrayList<>();

        for(String[] columna : fila){
            RespuestasxReclamoDTO dto = new RespuestasxReclamoDTO();
            dto.setTitulo_reclamo(columna[0]);
            dto.setUsername(columna[1]);
            dto.setCantidadRespuestas(Integer.parseInt(columna[2]));
        }
        return dtoLista;
    }
}
