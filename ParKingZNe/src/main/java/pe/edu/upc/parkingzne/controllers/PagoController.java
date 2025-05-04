package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.PagoDTO;
import pe.edu.upc.parkingzne.dtos.UsuarioPagoDTO;
import pe.edu.upc.parkingzne.entities.Pago;
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

        List<PagoDTO> pagos = gS.list().stream().map(a -> {
            ModelMapper m = new ModelMapper();
            return m.map(a, PagoDTO.class);
        }).collect(Collectors.toList());

        logger.info("Total pagos recuperados: {}", pagos.size());
        return pagos;
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public void insertar(@RequestBody PagoDTO dto) {
        logger.info("Insertando pago...");

        ModelMapper m = new ModelMapper();
        Pago pg = m.map(dto, Pago.class);

        gS.insert(pg);
        logger.info("Pago insertado correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public PagoDTO buscarId(@PathVariable("id") int id) {
        logger.info("Buscando pago con ID: {}", id);

        ModelMapper m = new ModelMapper();
        PagoDTO dto = m.map(gS.listId(id), PagoDTO.class);

        logger.info("Pago encontrado: {}", dto);
        return dto;
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public void modificar(@RequestBody PagoDTO dto) {
        logger.info("Modificando pago...");

        ModelMapper m = new ModelMapper();
        Pago pg = m.map(dto, Pago.class);

        gS.update(pg);
        logger.info("Pago modificado correctamente.");
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public void eliminar(@PathVariable("id") int id) {
        try {
            logger.info("Eliminando pago con ID: {}", id);
            gS.delete(id);
            logger.info("Pago eliminado correctamente.");
        } catch (Exception e) {
            logger.error("Error al eliminar pago con ID {}: {}", id, e.getMessage());
        }
    }

    @GetMapping("/cantidad-pagos-usuario")
    @PreAuthorize("hasAnyAuthority('ADSUB')")
    public List<UsuarioPagoDTO> cantidadPagosUsuario() {
        logger.info("Obteniendo pagos por usuarios...");

        List<String[]> fila = gS.cantidadPagosxUsuario();
        logger.info("Cantidad de pagos recuperados: {}", fila.size());
        List<UsuarioPagoDTO> dtoLista = new ArrayList<>();

        for(String[] columna : fila) {
            UsuarioPagoDTO dto = new UsuarioPagoDTO();
            dto.setId(Integer.parseInt(columna[0]));
            dto.setNombre(columna[1]);
            dto.setApellidos(columna[2]);
            dto.setCantidadPagos(Integer.parseInt(columna[3]));
        }
        logger.info("Cantidad de pagos por usuarios generada correctamente con {} elementos.", dtoLista.size());
        return dtoLista;
    }

}
