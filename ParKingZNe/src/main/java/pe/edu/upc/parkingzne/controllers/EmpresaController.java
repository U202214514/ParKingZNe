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
import pe.edu.upc.parkingzne.dtos.EmpresaDTO;
import pe.edu.upc.parkingzne.dtos.EmpresaReservaDTO;
import pe.edu.upc.parkingzne.entities.Empresa;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IEmpresaService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    private IEmpresaService eS;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public List<EmpresaDTO> listar(){
        logger.info("Listando todas las empresas registradas en el sistema");
        return eS.list().stream().map(p->{
            ModelMapper m = new ModelMapper();
            return m.map(p, EmpresaDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public ResponseEntity<String> insertar(@Valid @RequestBody EmpresaDTO dto) {
        logger.info("Insertando nueva empresa");
        if (dto.getNombreEmpresa() == null || dto.getNombreEmpresa().isBlank()) {
            throw new RequestBodyException("El nombre de la empresa es obligatorio.");
        }

        ModelMapper m = new ModelMapper();
        Empresa e = m.map(dto, Empresa.class);
        eS.insert(e);
        return new ResponseEntity<>("Empresa registrada correctamente", HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public EmpresaDTO buscarId(@PathVariable("id") @Min(1) int id) {
        logger.info("Buscando empresa con ID: {}", id);
        ModelMapper m = new ModelMapper();
        EmpresaDTO dto = m.map(eS.listId(id), EmpresaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public ResponseEntity<String> modificar(@Valid @RequestBody EmpresaDTO dto) {
        logger.info("Modificando empresa con ID: {}", dto.getIdEmpresa());
        ModelMapper m = new ModelMapper();
        Empresa e = m.map(dto, Empresa.class);
        eS.update(e);
        return new ResponseEntity<>("Empresa modificada correctamente", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) int id) {
        logger.info("Eliminando empresa con ID: {}", id);
        eS.delete(id);
        return new ResponseEntity<>("Empresa eliminada correctamente", HttpStatus.OK);
    }

    @GetMapping("/empresa-con-mas-reservas")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public List<EmpresaReservaDTO> listarEmpresaConMasReservas() {
        logger.info("Reportando las empresas con más reservas");
        List<String[]> fila = eS.empresaMasReservada();
        List<EmpresaReservaDTO> dtoLista = new ArrayList<>();

        for (String[] columna : fila) {
            EmpresaReservaDTO dto = new EmpresaReservaDTO();
            dto.setIdEmpresa(Integer.parseInt(columna[0]));
            dto.setNombreEmpresa(columna[1]);
            dto.setTotalReservas(Integer.parseInt(columna[2]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }
}
