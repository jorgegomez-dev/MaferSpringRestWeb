package gm.mafer.service;

import gm.mafer.model.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoService {

    List<Pedido> findAllPedidos();

    List<Pedido> findAllPedidosActivos();

    List<Pedido> findAllPedidosActivosByIdUsuario(Long idUsuario);

    Pedido crearPedido(Pedido pedido);

    Pedido findPedidoById(Long pedidoId);

    List<Pedido> findAllPedidosByIdUsuario(Long usuarioId);

    List<Pedido> findAllPedidosByFecha(LocalDate fecha);

    Pedido actualizarPedido (Pedido pedido);

    Pedido actualizarEstado (Pedido pedido);

    Boolean deletePedido (Long id);
}
