package utez.edu.mx.ejercicio.repository;

import utez.edu.mx.ejercicio.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUsuarioId(Long usuarioId);
}
