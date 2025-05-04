package gm.mafer.service.impl;

import gm.mafer.model.Pedido;
import gm.mafer.repository.PedidoRepository;
import gm.mafer.repository.ProductoRepository;
import gm.mafer.service.PedidoService;
import gm.mafer.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository; // Creamos un repositorio para traer el objeto pedido

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) { // Constructor de ProductoServiceImpl
        this.pedidoRepository = pedidoRepository;
    }

    @Autowired
    public ProductoRepository productoRepository;

    @Autowired
    public ProductoService productoService;

    @Override
    public List<Pedido> findAllPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos;
    }

    @Override
    public List<Pedido> findAllPedidosActivos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Pedido> pedidosActivos = new ArrayList<>();
        String estado = "activo";
        for (int i = 0; i < pedidos.size(); i++) {
            if(pedidos.get(i).getEstado().equals(estado)){
                pedidosActivos.add(pedidos.get(i));
            }
        }
        return pedidosActivos;
    }

    @Override
    public List<Pedido> findAllPedidosActivosByIdUsuario(Long idUsuario) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Pedido> pedidosActivosPorUsuario = new ArrayList<>();
        String estado = "activo";
        for (int i = 0; i < pedidos.size(); i++) {
            if(pedidos.get(i).getEstado().equals(estado) && pedidos.get(i).getIdUsuario().equals(idUsuario)){
                pedidosActivosPorUsuario.add(pedidos.get(i));
            }
        }
        return pedidosActivosPorUsuario;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido findPedidoById(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).get();
        return pedido;
    }

    // Devuelve una lista historica de pedidos de un usuario
    @Override
    public List<Pedido> findAllPedidosByIdUsuario(Long usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Pedido> pedidosPorId = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            if(pedidos.get(i).getIdUsuario() == usuarioId){
                pedidosPorId.add(pedidos.get(i));
            }
        }
        return pedidosPorId;
    }

    @Override
    public List<Pedido> findAllPedidosByFecha(LocalDate fecha) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Pedido> pedidosPorFecha = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < pedidos.size(); i++) {
            String fechaPedidoOriginalYear = pedidos.get(i).getCreatedOn().format(formatter);
            String fechaBusqueda = fecha.format(formatter);
            if(fechaPedidoOriginalYear.equals(fechaBusqueda)){
                pedidosPorFecha.add(pedidos.get(i));
            }
        }
        return pedidosPorFecha;
    }

    @Override
    public Pedido actualizarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido actualizarEstado(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Boolean deletePedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        if(pedido != null) {
            pedidoRepository.delete(pedido);
            return true;
        } else {
            return false;
        }
    }


}
