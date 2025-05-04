package gm.mafer.controller;

import gm.mafer.model.Pedido;
import gm.mafer.repository.PedidoRepository;
import gm.mafer.service.PedidoService;
import gm.mafer.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    public PedidoController(PedidoRepository pedidoRepository, PedidoService pedidoService, ProductoService productoService) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @GetMapping("/listarPedidos")
    public ResponseEntity<?> listarPedidos(){
        try{
            return new ResponseEntity<>(pedidoService.findAllPedidos(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarPedidosActivos")
    public ResponseEntity<?> listarPedidosActivos(){
        try{
            return new ResponseEntity<>(pedidoService.findAllPedidosActivos(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarPedidosActivosPorIdUsuario/{idUsuario}")
    public ResponseEntity<?> listarPedidosActivosPorIdUsuario(@PathVariable Long idUsuario){
        try{
            return new ResponseEntity<>(pedidoService.findAllPedidosActivosByIdUsuario(idUsuario), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarPedidosPorUsuario/{idUsuario}")
    public ResponseEntity<?> listarPedidosPorUsuario(@PathVariable Long idUsuario){
        try{
            return new ResponseEntity<>(pedidoService.findAllPedidosByIdUsuario(idUsuario), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listarPedidosPorFecha")
    public ResponseEntity<?> listarPedidosPorFecha(@RequestBody Map<String, String> fechaMap) {
        try {
            // Extraer la fecha del cuerpo del request
            String fechaString = fechaMap.get("fecha");

            // Formatear la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta al formato que envíes
            LocalDate fecha = LocalDate.parse(fechaString, formatter);

            return new ResponseEntity<>(pedidoService.findAllPedidosByFecha(fecha), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/crearPedido")
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
        Pedido pedidoCreado = pedidoService.crearPedido(pedido);

        if(pedidoCreado!= null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest() // Toma la url base de la solicitud
                    .path("/{id}")
                    .buildAndExpand(pedido.getId())
                    .toUri();
            if(pedidoCreado.getIdProductoUno()!=null){
                productoService.actualizarStock(pedidoCreado.getIdProductoUno(), pedidoCreado.getCantProductoUno());
            }
            if(pedidoCreado.getIdProductoDos()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoDos(), pedidoCreado.getCantProductoDos());
            }
            if(pedidoCreado.getIdProductoTres()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoTres(), pedidoCreado.getCantProductoTres());
            }
            if(pedidoCreado.getIdProductoCuatro()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoCuatro(), pedidoCreado.getCantProductoCuatro());
            }
            if(pedidoCreado.getIdProductoCinco()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoCinco(), pedidoCreado.getCantProductoCinco());
            }
            if(pedidoCreado.getIdProductoSeis()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoSeis(), pedidoCreado.getCantProductoSeis());
            }
            if(pedidoCreado.getIdProductoSiete()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoSiete(), pedidoCreado.getCantProductoSiete());
            }
            if(pedidoCreado.getIdProductoOcho()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoOcho(), pedidoCreado.getCantProductoOcho());
            }
            if(pedidoCreado.getIdProductoNueve()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoNueve(), pedidoCreado.getCantProductoNueve());
            }
            if(pedidoCreado.getIdProductoDiez()!=null) {
                productoService.actualizarStock(pedidoCreado.getIdProductoDiez(), pedidoCreado.getCantProductoDiez());
            }
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido crear el Pedido");
        }


    }

    @GetMapping("/buscarPedidoId/{id}")
    public ResponseEntity<?> buscarPedidoById (@PathVariable Long id){
        try{
            return new ResponseEntity<>(pedidoService.findPedidoById(id), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
    }

    @PutMapping("/updatePedido/{pedidoId}")
    public ResponseEntity<?> updatePedido(@PathVariable Long pedidoId, @RequestBody Pedido pedido){

        Pedido updatePedido = pedidoService.findPedidoById(pedidoId);
        if(updatePedido != null) {
            updatePedido.setIdProductoUno(pedido.getIdProductoUno());
            updatePedido.setCantProductoUno(pedido.getCantProductoUno());
            updatePedido.setIdProductoDos(pedido.getIdProductoDos());
            updatePedido.setCantProductoDos(pedido.getCantProductoDos());
            updatePedido.setIdProductoTres(pedido.getIdProductoTres());
            updatePedido.setCantProductoTres(pedido.getCantProductoTres());
            updatePedido.setIdProductoCuatro(pedido.getIdProductoCuatro());
            updatePedido.setCantProductoCuatro(pedido.getCantProductoCuatro());
            updatePedido.setIdProductoCinco(pedido.getIdProductoCinco());
            updatePedido.setCantProductoCinco(pedido.getCantProductoCinco());
            updatePedido.setIdProductoSeis(pedido.getIdProductoSeis());
            updatePedido.setCantProductoSeis(pedido.getCantProductoSeis());
            updatePedido.setIdProductoSiete(pedido.getIdProductoSiete());
            updatePedido.setCantProductoSiete(pedido.getCantProductoSiete());
            updatePedido.setIdProductoOcho(pedido.getIdProductoOcho());
            updatePedido.setCantProductoOcho(pedido.getCantProductoOcho());
            updatePedido.setIdProductoNueve(pedido.getIdProductoNueve());
            updatePedido.setCantProductoNueve(pedido.getCantProductoNueve());
            updatePedido.setIdProductoDiez(pedido.getIdProductoDiez());
            updatePedido.setCantProductoDiez(pedido.getCantProductoDiez());

            pedidoService.actualizarPedido(updatePedido);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatePedidoEstado/{pedidoId}")
    public ResponseEntity<?> updatePedidoEstado(@PathVariable Long pedidoId, @RequestBody String nuevoEstado){

        Pedido updatePedido = pedidoService.findPedidoById(pedidoId);
        if(!Objects.equals(updatePedido.getEstado(), nuevoEstado)) {
            updatePedido.setEstado(nuevoEstado);
            pedidoService.actualizarEstado(updatePedido);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletePedido/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(pedidoService.deletePedido(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
