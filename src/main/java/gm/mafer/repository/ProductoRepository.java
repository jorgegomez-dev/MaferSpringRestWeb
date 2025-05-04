package gm.mafer.repository;

import gm.mafer.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository <Producto, Long>{
}
