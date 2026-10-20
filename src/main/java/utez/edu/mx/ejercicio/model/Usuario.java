package utez.edu.mx.ejercicio.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Builder.Default
    @Column(nullable = false)
    private Boolean activo = true;
}