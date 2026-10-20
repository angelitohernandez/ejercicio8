package utez.edu.mx.ejercicio.dto;


import utez.edu.mx.ejercicio.model.PrioridadTicket;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReasignarPrioridadDTO {

    @NotNull(message = "La nueva prioridad es obligatoria")
    private PrioridadTicket nuevaPrioridad;
}