package pe.edu.upc.parkingzne.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.parkingzne.dtos.UsuarioDTO;
import pe.edu.upc.parkingzne.entities.Usuario;
import pe.edu.upc.parkingzne.servicesinterfaces.IUsuarioService;


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
    public void insertar(@RequestBody UsuarioDTO dto) {
        logger.info("Registrando nuevo usuario con el ID: {}", dto.getId_usuario());
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        uS.insertar(u);
    }

    @GetMapping ("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UsuarioDTO listID(@PathVariable("id") Integer id){
        logger.info("Buscando usuario con ID: {}", id);
        ModelMapper m =new ModelMapper();
        UsuarioDTO dto = m.map(uS.listID(id),UsuarioDTO.class);
        return dto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") Integer id) {
        logger.info("Eliminando usuario con ID: {}", id);
        uS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('SUB')")
    public void modificar(@RequestBody UsuarioDTO dto) {
        logger.info("Modificando usuario con ID: {}", dto.getId_usuario());
        ModelMapper m = new ModelMapper();
        Usuario d = m.map(dto, Usuario.class);
        uS.update(d);
    }
    @GetMapping("/usuario_rol")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UsuarioDTO> usuarioporrol(){
        return uS.listarUsuarios().stream().map( x-> {
            ModelMapper m =new ModelMapper();
            return m.map(x,UsuarioDTO.class);
        }).collect(Collectors.toList());

    }


}
