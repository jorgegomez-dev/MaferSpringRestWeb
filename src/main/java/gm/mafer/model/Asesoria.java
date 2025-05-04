package gm.mafer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity // Declara esta clase como una Entidad que va a tener una tabla en una base de datos
@Builder  // Implementa el patron Builder para la construccion de Objetos
@NoArgsConstructor // Agrega el constructor vacio
@AllArgsConstructor // Agrega el constructor con todos los argumentos
@Getter
@Setter
@Table(name = "asesorias")
public class Asesoria {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombreAsesoria;
    private String tipoAsesoria;
    private String pdfUrl;
    private Double precio;
    private String imgUrl;
    private String linkPago;
}
