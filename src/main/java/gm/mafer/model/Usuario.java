package gm.mafer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.*;

@Entity // Declara esta clase como una Entidad que va a tener una tabla en una base de datos
@Builder  // Implementa el patron Builder para la construccion de Objetos
@NoArgsConstructor // Agrega el constructor vacio
@AllArgsConstructor // Agrega el constructor con todos los argumentos
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Incrementa el valor de Id en 1 cada vez que se crea un usuario
    private Long id;
    @NotEmpty (message = "El campo 'nombre' no puede estar vacío.")
    private String nombre;
    @NotEmpty (message = "El campo 'apellido' no puede estar vacío.")
    private String apellido;
    @NotEmpty (message = "El campo 'email' no puede estar vacío.")
    private String email;
    @NotEmpty (message = "El campo 'teléfono' no puede estar vacío.")
    private String telefono;
    @NotEmpty (message = "El campo 'username' no puede estar vacío.")
    private String username;
    @NotEmpty (message = "El campo 'password' no puede estar vacío.")
    private String password;
    private String role;
    private Double peso;
    private Double altura;
    private Double imc;
    private String sexo;
    private Long idRutinaActual;
    private LocalDate fechaInicioRutina; // Cuando se debe comenzar la rutina
    private String urlfotoperfil;

    @CreationTimestamp
    private LocalDateTime createdOn; // Fecha y hora de creacion de la cuenta
    @UpdateTimestamp
    private LocalDateTime updatedOn; // Fecha y hora de ultima actualizacion de datos

    private LocalDate birthdate; // Fecha de nacimiento que se guarda en base de datos
    private Integer edad;

    private Long idAsesoriaActual;
    private LocalDate fechaInicioAsesoria;
    private LocalDate fechaFinAsesoria;

    private boolean Authenticated = false; // Inicializa en false, cada usuario nuevo inicializa no autenticado
    private Integer session;


}
