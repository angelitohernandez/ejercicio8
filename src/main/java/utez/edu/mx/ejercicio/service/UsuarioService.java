package utez.edu.mx.ejercicio.service;

import utez.edu.mx.ejercicio.exception.ResourceNotFoundException;
import utez.edu.mx.ejercicio.model.Usuario;
import utez.edu.mx.ejercicio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
}