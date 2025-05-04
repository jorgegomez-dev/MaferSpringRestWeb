package gm.mafer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity // Declara esta clase como una Entidad que va a tener una tabla en una base de datos
@Builder  // Implementa el patron Builder para la construccion de Objetos
@NoArgsConstructor // Agrega el constructor vacio
@AllArgsConstructor // Agrega el constructor con todos los argumentos
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;

    // Carrito Max. 10 productos por pedido
    private Long idProductoUno;
    private Integer cantProductoUno;
    private Long idProductoDos;
    private Integer cantProductoDos;
    private Long idProductoTres;
    private Integer cantProductoTres;
    private Long idProductoCuatro;
    private Integer cantProductoCuatro;
    private Long idProductoCinco;
    private Integer cantProductoCinco;
    private Long idProductoSeis;
    private Integer cantProductoSeis;
    private Long idProductoSiete;
    private Integer cantProductoSiete;
    private Long idProductoOcho;
    private Integer cantProductoOcho;
    private Long idProductoNueve;
    private Integer cantProductoNueve;
    private Long idProductoDiez;
    private Integer cantProductoDiez;

    // Los precios estan expresados en pesos con iva incluido
    private Double precioTotal;

    // La variable se inicializa como activo apenas se crea el pedido y se guarda en DB
    // Luego hay que pasarlo manualmente desde el panel de administrador una vez que hay sido entregado
    private String estado = "activo";    // activo, entregado

    @CreationTimestamp
    private LocalDateTime createdOn; // Fecha y hora de creacion del pedido

}
