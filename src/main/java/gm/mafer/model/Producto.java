package gm.mafer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Declara esta clase como una Entidad que va a tener una tabla en una base de datos
@Builder  // Implementa el patron Builder para la construccion de Objetos
@NoArgsConstructor // Agrega el constructor vacio
@AllArgsConstructor // Agrega el constructor con todos los argumentos
@Getter
@Setter
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProducto;
    private String tipoDeProducto;
    private String marca;
    private String descripcion;
    private Integer stock;

    // El precio esta expresado en pesos con iva incluido
    private Double precio;
    private String imgUrl;
    private String linkPago;

}
