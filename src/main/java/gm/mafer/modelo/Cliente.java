package gm.mafer.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data // Genera los metodos Getter y Setter automaticamente
@NoArgsConstructor // Agrega el constructor vacio
@AllArgsConstructor // Agrega el constructor con todos los argumentos
@ToString
@EqualsAndHashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer peso;
}
