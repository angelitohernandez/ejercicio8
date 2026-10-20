package utez.edu.mx.ejercicio.service;

import utez.edu.mx.ejercicio.dto.ReasignarPrioridadDTO;
import utez.edu.mx.ejercicio.dto.TicketCreateDTO;
import utez.edu.mx.ejercicio.dto.TicketResponseDTO;
import utez.edu.mx.ejercicio.exception.BadRequestException;
import utez.edu.mx.ejercicio.exception.ResourceNotFoundException;
import utez.edu.mx.ejercicio.model.EstadoTicket;
import utez.edu.mx.ejercicio.model.Ticket;
import utez.edu.mx.ejercicio.model.Usuario;
import utez.edu.mx.ejercicio.repository.TicketRepository;
import utez.edu.mx.ejercicio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketResponseDTO crearTicket(TicketCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new BadRequestException("No se puede crear un ticket para un usuario inactivo");
        }

        Ticket ticket = Ticket.builder()
                .descripcion(dto.getDescripcion())
                .estado(EstadoTicket.ABIERTO)
                .prioridad(dto.getPrioridad())
                .usuario(usuario)
                .build();

        Ticket guardado = ticketRepository.save(ticket);
        return mapToDTO(guardado);
    }

    public TicketResponseDTO actualizarEstadoTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con ID: " + ticketId));

        switch (ticket.getEstado()) {
            case ABIERTO:
                ticket.setEstado(EstadoTicket.EN_PROCESO);
                break;
            case EN_PROCESO:
                ticket.setEstado(EstadoTicket.CERRADO);
                break;
            case CERRADO:
                throw new BadRequestException("El ticket ya está CERRADO y no se puede modificar");
        }

        Ticket actualizado = ticketRepository.save(ticket);
        return mapToDTO(actualizado);
    }

    public TicketResponseDTO reasignarPrioridad(Long ticketId, ReasignarPrioridadDTO dto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con ID: " + ticketId));

        if (EstadoTicket.CERRADO.equals(ticket.getEstado())) {
            throw new BadRequestException("No se puede cambiar la prioridad de un ticket CERRADO");
        }

        ticket.setPrioridad(dto.getNuevaPrioridad());
        Ticket actualizado = ticketRepository.save(ticket);
        return mapToDTO(actualizado);
    }

    public List<TicketResponseDTO> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + usuarioId);
        }

        return ticketRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private TicketResponseDTO mapToDTO(Ticket ticket) {
        return TicketResponseDTO.builder()
                .id(ticket.getId())
                .descripcion(ticket.getDescripcion())
                .estado(ticket.getEstado())
                .prioridad(ticket.getPrioridad())
                .usuarioId(ticket.getUsuario().getId())
                .nombreUsuario(ticket.getUsuario().getNombre())
                .build();
    }
}
