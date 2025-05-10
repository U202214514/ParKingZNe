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
import pe.edu.upc.parkingzne.dtos.CantidadRolesRegistradosDTO;
import pe.edu.upc.parkingzne.dtos.RolDTO;
import pe.edu.upc.parkingzne.entities.Rol;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IRolService;

import java.util.ArrayList;
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
        logger.info("Listando todos los roles del sistema");
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RolDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> insertar(@Valid @RequestBody RolDTO dto) {
        logger.info("Insertando nuevo rol");

        if (dto.getTipoRol() == null || dto.getTipoRol().isBlank()) {
            throw new RequestBodyException("El nombre del rol no puede estar vacío.");
        }

        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        rS.insert(r);

        return ResponseEntity.status(HttpStatus.CREATED).body("Rol registrado correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RolDTO listID(@PathVariable("id") @Min(1) Integer id) {
        logger.info("Buscando rol con ID: {}", id);
        ModelMapper m = new ModelMapper();
        RolDTO dto = m.map(rS.listId(id), RolDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@Valid @RequestBody RolDTO dto) {
        logger.info("Modificando Rol con ID: {}", dto.getIdRol());
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        rS.update(r);
        return ResponseEntity.ok("Rol modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) Integer id) {
        logger.info("Eliminando Rol con ID: {}", id);
        rS.delete(id);
        return ResponseEntity.ok("Rol eliminado correctamente.");
    }

    @GetMapping("/cantidad-roles")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<CantidadRolesRegistradosDTO> cantidadRolesRegistrados() {
        logger.info("Generando reporte de cantidad de roles por tipo");

        List<Object[]> fila = rS.CantidadRolesxTipo();
        List<CantidadRolesRegistradosDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            CantidadRolesRegistradosDTO dto = new CantidadRolesRegistradosDTO();
            dto.setNombre_del_rol((String) columna[0]);
            dto.setCantidad(((Number) columna[1]).intValue());
            dtoLista.add(dto);
        }

        return dtoLista;
    }
}