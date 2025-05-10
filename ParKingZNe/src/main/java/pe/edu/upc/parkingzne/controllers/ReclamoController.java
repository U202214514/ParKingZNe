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
import pe.edu.upc.parkingzne.dtos.ReclamoDTO;
import pe.edu.upc.parkingzne.dtos.ReporteReclamosDTO;
import pe.edu.upc.parkingzne.entities.Reclamo;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IReclamoService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reclamos")
public class ReclamoController {

    private static final Logger logger = LoggerFactory.getLogger(ReclamoController.class);

    @Autowired
    private IReclamoService mS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public List<ReclamoDTO> list() {
        logger.info("Listando todos los reclamos registrados por los usuarios");
        return mS.list().stream().map(l -> {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(l, ReclamoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public ResponseEntity<String> insertar(@Valid @RequestBody ReclamoDTO dto) {
        logger.info("Insertando reclamo por parte del usuario");

        if (dto.getDescripcion() == null || dto.getDescripcion().isBlank()) {
            throw new RequestBodyException("La descripción del reclamo es obligatoria.");
        }

        ModelMapper modelMapper = new ModelMapper();
        Reclamo rc = modelMapper.map(dto, Reclamo.class);
        mS.insert(rc);

        return ResponseEntity.status(HttpStatus.CREATED).body("Reclamo registrado correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public ReclamoDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando reclamo con ID: {}", id);
        ModelMapper modelMapper = new ModelMapper();
        ReclamoDTO dto = modelMapper.map(mS.listId(id), ReclamoDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public ResponseEntity<String> modificar(@Valid @RequestBody ReclamoDTO dto) {
        logger.info("Modificando reclamo con ID: {}", dto.getIdReclamo());

        ModelMapper modelMapper = new ModelMapper();
        Reclamo rc = modelMapper.map(dto, Reclamo.class);
        mS.update(rc);

        return ResponseEntity.status(HttpStatus.OK).body("Reclamo modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando reclamo con ID: {}", id);
        mS.delete(id);
        return ResponseEntity.ok("Reclamo eliminado correctamente.");
    }

    @GetMapping("/reportes-reclamos-usuarios")
    @PreAuthorize("hasAnyAuthority('ADRCL')")
    public List<ReporteReclamosDTO> reportesReclamosxUsuario() {
        logger.info("Generando reportes de reclamos por usuario");
        List<Object[]> fila = mS.ReporteReclamosUsuario();
        List<ReporteReclamosDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            ReporteReclamosDTO dto = new ReporteReclamosDTO();
            dto.setId_usuario(((Number) columna[0]).intValue());
            dto.setUsername((String) columna[1]);
            dto.setFecha_reclamo(((java.sql.Date) columna[2]).toLocalDate());
            dto.setCantidad_reclamos(((Number) columna[3]).intValue());
            dto.setReclamos_con_reserva(((Number) columna[4]).intValue());
            dtoLista.add(dto);
        }

        return dtoLista;
    }
}
