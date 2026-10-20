package utez.edu.mx.ejercicio.controller;

import utez.edu.mx.ejercicio.dto.ReasignarPrioridadDTO;
import utez.edu.mx.ejercicio.dto.TicketCreateDTO;
import utez.edu.mx.ejercicio.dto.TicketResponseDTO;
import utez.edu.mx.ejercicio.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> crearTicket(@Valid @RequestBody TicketCreateDTO dto) {
        return new ResponseEntity<>(ticketService.crearTicket(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TicketResponseDTO> actualizarEstadoTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<TicketResponseDTO> reasignarPrioridad(
            @PathVariable Long id,
            @Valid @RequestBody ReasignarPrioridadDTO dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketResponseDTO>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}