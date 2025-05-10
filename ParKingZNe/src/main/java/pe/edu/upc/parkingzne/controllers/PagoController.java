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
import pe.edu.upc.parkingzne.dtos.PagoDTO;
import pe.edu.upc.parkingzne.dtos.UsuarioPagoDTO;
import pe.edu.upc.parkingzne.entities.Pago;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IPagoService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private static final Logger logger = LoggerFactory.getLogger(PagoController.class);

    @Autowired
    private IPagoService gS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public List<PagoDTO> list() {
        logger.info("Obteniendo lista de pagos...");
        return gS.list().stream().map(pago -> {
            ModelMapper m = new ModelMapper();
            return m.map(pago, PagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public ResponseEntity<String> insertar(@Valid @RequestBody PagoDTO dto) {
        logger.info("Insertando pago...");

        if (dto.getMontoPago() <= 0 || dto.getFechaPago() == null) {
            throw new RequestBodyException("Los campos 'montoPago' debe ser mayor a 0 y 'fechaPago' es obligatorio.");
        }

        ModelMapper m = new ModelMapper();
        Pago pago = m.map(dto, Pago.class);

        gS.insert(pago);
        logger.info("Pago insertado correctamente.");

        return ResponseEntity.status(HttpStatus.CREATED).body("Pago registrado correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public PagoDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando pago con ID: {}", id);
        ModelMapper m = new ModelMapper();
        PagoDTO dto = m.map(gS.listId(id), PagoDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public ResponseEntity<String> modificar(@Valid @RequestBody PagoDTO dto) {
        logger.info("Modificando pago con ID: {}", dto.getIdPago());

        ModelMapper m = new ModelMapper();
        Pago pago = m.map(dto, Pago.class);
        gS.update(pago);

        return ResponseEntity.status(HttpStatus.OK).body("Pago modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando pago con ID: {}", id);
        gS.delete(id);
        return ResponseEntity.ok("Pago eliminado correctamente.");
    }

    @GetMapping("/cantidad-pagos-usuario")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public List<UsuarioPagoDTO> cantidadPagosUsuario() {
        logger.info("Generando cantidad de pagos por usuario...");

        List<Object[]> fila = gS.cantidadPagosxUsuario();
        List<UsuarioPagoDTO> dtoLista = new ArrayList<>();

        for (Object[] columna : fila) {
            UsuarioPagoDTO dto = new UsuarioPagoDTO();
            dto.setId(((Number) columna[0]).intValue());
            dto.setNombre((String) columna[1]);
            dto.setApellidos((String) columna[2]);
            dto.setCantidadPagos(((Number) columna[3]).intValue());
            dtoLista.add(dto);
        }

        logger.info("Cantidad de pagos generada correctamente: {} registros.", dtoLista.size());
        return dtoLista;
    }

}
