package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.RolDTO;
import pe.edu.upc.parkingzne.entities.Rol;
import pe.edu.upc.parkingzne.servicesinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rol")
public class RolController {

    private static final Logger logger = LoggerFactory.getLogger(RolController.class);

    @Autowired
    private IRolService rS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RolDTO> listar() {
        logger.info("Listando todos los roles existentes en el sistema web ParkinZon");
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RolDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody RolDTO dto) {
        logger.info("Insertando nuevo rol para el usuario con el ID: {}", dto.getIdRol());
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        rS.insert(r);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RolDTO listID(@PathVariable("id") Integer id) {
        logger.info("Buscando rol con ID: {}", id);
        ModelMapper m = new ModelMapper();
        RolDTO dto = m.map(rS.listId(id), RolDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void modificar(@RequestBody RolDTO dto) {
        logger.info("Modificando Rol con ID: {}", dto.getIdRol());
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        rS.update(r);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") Integer id) {
        logger.info("Eliminando Rol con ID: {}", id);
        rS.delete(id);
    }
}