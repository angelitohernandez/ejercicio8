package utez.edu.mx.ejercicio.dto;

import utez.edu.mx.ejercicio.model.PrioridadTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketCreateDTO {

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioId;
}