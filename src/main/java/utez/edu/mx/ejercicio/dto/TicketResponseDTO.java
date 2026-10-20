package utez.edu.mx.ejercicio.dto;

import utez.edu.mx.ejercicio.model.EstadoTicket;
import utez.edu.mx.ejercicio.model.PrioridadTicket;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketResponseDTO {
    private Long id;
    private String descripcion;
    private EstadoTicket estado;
    private PrioridadTicket prioridad;
    private Long usuarioId;
    private String nombreUsuario;
}