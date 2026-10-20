package utez.edu.mx.ejercicio.dto;

import utez.edu.mx.ejercicio.model.EstadoTicket;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarEstadoDTO {

    @NotNull(message = "El nuevo estado no puede ser nulo")
    private EstadoTicket estado;
}