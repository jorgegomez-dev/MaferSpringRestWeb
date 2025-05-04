package gm.mafer.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEjercicio;
    private String sexo;
    private Integer cantSeriesMin;
    private Integer cantSeriesMax;
    private Integer cantRepeticionesMin;
    private Integer cantRepeticionesMax;
    private Integer descansoMinutosMin;
    private Integer descansoMinutosMax;
    private String musculo;
    private String urlimgejercicio;


}
