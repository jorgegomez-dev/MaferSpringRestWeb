package gm.mafer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreRutina;
    private String sexo;
    // private LocalDate fechaInicioRutina; // Fecha del Lunes mas proximo
    private Long diaUno;
    private Long diaDos;
    private Long diaTres;
    private Long diaCuatro;
    private Long diaCinco;
    private Long diaSeis;
    private Long diaSiete;

    @CreationTimestamp
    private LocalDateTime createdOn; // Fecha y hora de creacion de la cuenta
    @UpdateTimestamp
    private LocalDateTime updatedOn; // Fecha y hora de ultima actualizacion de datos



}
