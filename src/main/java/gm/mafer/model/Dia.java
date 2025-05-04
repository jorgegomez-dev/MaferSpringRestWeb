package gm.mafer.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "dias")
public class Dia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreDia; // Ejemplo 'Piernas y Gluteos'
    private String sexo;
    private Long ejercicioUno; // Guarda el Id del ejercicio de la base de datos 'ejercicios'
    private Long ejercicioDos;
    private Long ejercicioTres;
    private Long ejercicioCuatro;
    private Long ejercicioCinco;
    private Long ejercicioSeis;
    private Long ejercicioSiete;
    private Long ejercicioOcho;
    private Long ejercicioNueve;
    private Long ejercicioDiez;

}
