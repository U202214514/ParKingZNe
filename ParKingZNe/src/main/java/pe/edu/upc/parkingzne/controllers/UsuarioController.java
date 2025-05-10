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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.UsuarioDTO;
import pe.edu.upc.parkingzne.dtos.UsuariosconEdad18Y40yearsDTO;
import pe.edu.upc.parkingzne.entities.Usuario;
import pe.edu.upc.parkingzne.exceptions.RequestBodyException;
import pe.edu.upc.parkingzne.servicesinterfaces.IUsuarioService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService uS;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UsuarioDTO> listarUsuarios(){
        logger.info("Listando todos los usuarios");
        return uS.listarUsuarios().stream().map( x-> {
            ModelMapper m =new ModelMapper();
            return m.map(x,UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> insertar(@Valid @RequestBody UsuarioDTO dto) {
        logger.info("Registrando nuevo usuario");

        if (dto.getUsername() == null || dto.getPassword() == null) {
            throw new RequestBodyException("El nombre de usuario y la contraseña son obligatorios.");
        }

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);

        uS.insertar(u);
        return new ResponseEntity<>("Usuario registrado correctamente.", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UsuarioDTO listID(@PathVariable("id") @Min(1) Integer id) {
        logger.info("Buscando usuario con ID: {}", id);
        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(uS.listID(id), UsuarioDTO.class);
        return dto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") @Min(1) Integer id) {
        logger.info("Eliminando usuario con ID: {}", id);
        uS.delete(id);
        return new ResponseEntity<>("Usuario eliminado correctamente.", HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB')")
    public ResponseEntity<String> modificar(@Valid @RequestBody UsuarioDTO dto) {
        logger.info("Modificando usuario con ID: {}", dto.getId_usuario());
        ModelMapper m = new ModelMapper();
        Usuario d = m.map(dto, Usuario.class);
        uS.update(d);
        return new ResponseEntity<>("Usuario modificado correctamente.", HttpStatus.OK);
    }

    @GetMapping("/usuario_rol")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UsuarioDTO> usuarioporrol() {
        logger.info("Listando usuarios por rol");
        return uS.listarUsuarios().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/usuarios-adultos")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<UsuariosconEdad18Y40yearsDTO> usuariosadultos() {
        logger.info("Generando reporte de usuarios entre 18 y 40 años");
        List<Object[]> fila = uS.usuariosConEdadentre18y40anos();
        List<UsuariosconEdad18Y40yearsDTO> dtolista = new ArrayList<>();

        for (Object[] columna : fila) {
            UsuariosconEdad18Y40yearsDTO dto = new UsuariosconEdad18Y40yearsDTO();
            dto.setIDUser(((Number) columna[0]).intValue());
            dto.setUSERNAME((String) columna[1]);
            dto.setFECHA(((java.sql.Date) columna[2]).toLocalDate());
            dto.setCORREO((String) columna[3]);
            dtolista.add(dto);
        }
        return dtolista;
    }

}
