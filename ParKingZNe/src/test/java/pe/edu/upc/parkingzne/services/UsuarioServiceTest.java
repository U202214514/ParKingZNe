package pe.edu.upc.parkingzne.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.parkingzne.entities.Usuario;
import pe.edu.upc.parkingzne.repositories.IUsuarioRepository;
import pe.edu.upc.parkingzne.servicesimplements.UsuarioServiceImplement;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImplement usuarioService;

    private Usuario user1;
    private Usuario user2;

    @BeforeEach
    void setUp() {
        user1 = new Usuario();
        user1.setId_usuario(1);
        user1.setNombre("Luis");
        user1.setApellidos("Pérez");
        user1.setCorreo("luis@example.com");
        user1.setUsername("luis123");
        user1.setPassword("123");
        user1.setTelefono("999999999");
        user1.setLatitudUsuario(-12.04);
        user1.setLongitudUsuario(-77.03);
        user1.setEnabled(true);
        user1.setFechanacimiento(LocalDate.of(2000, 1, 1));

        user2 = new Usuario();
        user2.setId_usuario(2);
        user2.setNombre("Ana");
        user2.setApellidos("García");
        user2.setCorreo("ana@example.com");
        user2.setUsername("ana456");
        user2.setPassword("456");
        user2.setTelefono("988888888");
        user2.setLatitudUsuario(-12.05);
        user2.setLongitudUsuario(-77.04);
        user2.setEnabled(true);
        user2.setFechanacimiento(LocalDate.of(1998, 5, 10));
    }

    @Test
    void insertar_Usuario() {
        usuarioService.insertar(user1);
        verify(usuarioRepository, times(1)).save(user1);
    }

    @Test
    void listarUsuarios_DeberiaRetornarLista() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<Usuario> resultado = usuarioService.listarUsuarios();

        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getUsername()).isEqualTo("luis123");
        verify(usuarioRepository, times(1)).findAll();
    }
}
