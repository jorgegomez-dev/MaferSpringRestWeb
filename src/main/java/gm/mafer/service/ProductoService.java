package gm.mafer.service;

import gm.mafer.model.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> findAllProductos();

    List<Producto> findAllProductosPorNombre(String nombre);

    List<Producto> findAllProductosPorMarca(String marca);

    List<Producto> findAllProductosPorTipo(String tipoProducto);

    Producto crearProducto(Producto producto);

    Producto findProductoById(Long productoId);

    Producto actualizarProducto (Producto producto);

    Boolean deleteProducto (Long id);

    Producto actualizarStock(Long idProducto, Integer cantidad);

}
