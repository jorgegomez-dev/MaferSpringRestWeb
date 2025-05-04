package gm.mafer.service.impl;

import gm.mafer.model.Producto;
import gm.mafer.repository.ProductoRepository;
import gm.mafer.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository; // Creamos un repositorio para traer el objeto producto

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) { // Constructor de ProductoServiceImpl
        this.productoRepository = productoRepository;
    }


    @Override
    public List<Producto> findAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }

    @Override
    public List<Producto> findAllProductosPorNombre(String nombre) {
        List<Producto> productos = productoRepository.findAll();
        List<Producto> productosPorNombre = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getNombreProducto().equalsIgnoreCase(nombre)){
                productosPorNombre.add(productos.get(i));
            }
        }
        return productosPorNombre;
    }

    @Override
    public List<Producto> findAllProductosPorMarca(String marca) {
        List<Producto> productos = productoRepository.findAll();
        List<Producto> productosPorMarca = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getMarca().equalsIgnoreCase(marca)){
                productosPorMarca.add(productos.get(i));
            }
        }
        return productosPorMarca;
    }

    @Override
    public List<Producto> findAllProductosPorTipo(String tipoProducto) {
        List<Producto> productos = productoRepository.findAll();
        List<Producto> productosPorTipo = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getTipoDeProducto().equalsIgnoreCase(tipoProducto)){
                productosPorTipo.add(productos.get(i));
            }
        }
        return productosPorTipo;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto findProductoById(Long productoId) {
        Producto producto = productoRepository.findById(productoId).get();
        return producto;
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Boolean deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id).get();
        if(producto != null) {
            productoRepository.delete(producto);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Producto actualizarStock(Long idProducto, Integer cantidad) {
        Producto producto = productoRepository.findById(idProducto).get();
        Integer nuevoStock = producto.getStock() - cantidad;
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

}
