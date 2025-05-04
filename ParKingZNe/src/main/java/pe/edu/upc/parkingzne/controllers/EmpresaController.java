package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.EmpresaDTO;
import pe.edu.upc.parkingzne.dtos.EmpresaReservaDTO;
import pe.edu.upc.parkingzne.entities.Empresa;
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
    public void insertar(@RequestBody EmpresaDTO dto) {
        logger.info("Insertando nueva empresa para los usuarios con ID: {}", dto.getIdEmpresa());
        ModelMapper m = new ModelMapper();
        Empresa e = m.map(dto, Empresa.class);
        eS.insert(e);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public EmpresaDTO buscarId(@PathVariable("id") int id) {
        logger.info("Buscando empresa con ID: {}", id);
        ModelMapper m = new ModelMapper();
        EmpresaDTO dto = m.map(eS, EmpresaDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public void modificar(@RequestBody EmpresaDTO dto) {
        logger.info("Modificando empresa con ID: {}", dto.getIdEmpresa());
        ModelMapper m = new ModelMapper();
        Empresa e = m.map(dto, Empresa.class);
        eS.update(e);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public void eliminar(@PathVariable("id") int id) {
        logger.info("Eliminando empresa con ID: {}", id);
        eS.delete(id);
    }

    @GetMapping("/empresa-con-mas-reservas")
    @PreAuthorize("hasAnyAuthority('ADEST')")
    public List<EmpresaReservaDTO> listarEmpresaConMasReservas(){
        logger.info("Reportando la empres(a)s con mas registro de reservas");
        List<String[]> fila = eS.empresaMasReservada();
        List<EmpresaReservaDTO> dtoLista = new ArrayList<>();

        for(String[] columna : fila){
            EmpresaReservaDTO dto = new EmpresaReservaDTO();
            dto.setIdEmpresa(Integer.parseInt(columna[0]));
            dto.setNombreEmpresa(columna[1]);
            dto.setTotalReservas(Integer.parseInt(columna[2]));
        }
        return dtoLista;
    }
}
