package gm.mafer.repository;

import gm.mafer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

     Optional<Usuario> findByUsername(String username);

     // Busca un usuario por Id, devuelve un Objeto Usuario completo
    //Optional<Usuario> findByUserId(String userIdUsuario);

    // Busca un usuario por username, devuelve un Objeto Usuario completo
    //Optional<Usuario> findByUserName(String username);
}
