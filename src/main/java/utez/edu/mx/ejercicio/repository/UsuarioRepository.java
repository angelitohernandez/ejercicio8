package utez.edu.mx.ejercicio.repository;
import utez.edu.mx.ejercicio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
