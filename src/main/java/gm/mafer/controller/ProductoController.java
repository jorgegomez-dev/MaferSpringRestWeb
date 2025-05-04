package gm.mafer.controller;

import gm.mafer.model.Producto;
import gm.mafer.repository.ProductoRepository;
import gm.mafer.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoRepository productoRepository, ProductoService productoService) {
        this.productoRepository = productoRepository;
        this.productoService = productoService;
    }

    @GetMapping("/listarProductos")
    public ResponseEntity<?> listarProductos(){
        try{
            return new ResponseEntity<>(productoService.findAllProductos(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarProductosPorNombre/{nombre}")
    public ResponseEntity<?> listarProductosPorNombre(@PathVariable String nombre){
        try{
            return new ResponseEntity<>(productoService.findAllProductosPorNombre(nombre), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarProductosPorMarca/{marca}")
    public ResponseEntity<?> listarProductosPorMarca(@PathVariable String marca){
        try{
            return new ResponseEntity<>(productoService.findAllProductosPorMarca(marca), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarProductosPorTipoProducto/{tipoProducto}")
    public ResponseEntity<?> listarProductosPorTipo(@PathVariable String tipoProducto){
        try{
            return new ResponseEntity<>(productoService.findAllProductosPorTipo(tipoProducto), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearProducto")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        Producto productoCreado = productoService.crearProducto(producto);
        if(productoCreado!= null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest() // Toma la url base de la solicitud
                    .path("/{id}")
                    .buildAndExpand(producto.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido crear el Producto");
        }
    }

    @GetMapping("/buscarProductoId/{id}")
    public ResponseEntity<?> buscarProductoById (@PathVariable Long id){
        try{
            return new ResponseEntity<>(productoService.findProductoById(id), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @PutMapping("/updateProducto/{productoId}")
    public ResponseEntity<?> updateProducto(@PathVariable Long productoId, @RequestBody Producto producto){

        Producto updateProducto = productoService.findProductoById(productoId);
        if(updateProducto != null) {
            updateProducto.setNombreProducto(producto.getNombreProducto());
            updateProducto.setTipoDeProducto(producto.getTipoDeProducto());
            updateProducto.setMarca(producto.getMarca());
            updateProducto.setDescripcion(producto.getDescripcion());
            updateProducto.setStock(producto.getStock());
            updateProducto.setPrecio(producto.getPrecio());
            updateProducto.setImgUrl(producto.getImgUrl());
            updateProducto.setLinkPago(producto.getLinkPago());
            productoService.actualizarProducto(updateProducto);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteProducto/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(productoService.deleteProducto(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
