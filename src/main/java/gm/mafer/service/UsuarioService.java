package gm.mafer.service;

import gm.mafer.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioService {

    List<Usuario> findAllUsuarios();

    List<Usuario> findAllUsuariosPorNombre(String nombre);

    List<Usuario> findAllUsuariosPorApellido(String apellido);

    List<Usuario> findAllUsuariosPorSexo(String sexo);

    List<Usuario> findAllUsuariosPorRol(String rol);

    List<Usuario> findAllUsuariosPorAsesoriaVencida(LocalDate fechaActual);

    Usuario crearUsuario(Usuario usuario);

    Usuario findUsuarioById(Long usuarioId);

    Usuario findUsuarioByUsername(String username);

    Boolean findUsuarioByUsernameExist(String username);

    List<Usuario> findAllUsuariosPorEmail(String email);

    Boolean findUsuarioByEmailExist(String email);

    Usuario actualizarUsuario (Usuario usuario);

    Boolean deleteUsuario (Long id);

    Double calcularIMC(Double peso, Double altura);

    Integer calcularEdad (LocalDate birthDate);

    LocalDate calcularFinAsesoria (LocalDate fechaInicioAsesoria);

    // ACTIVAR SI QUEREMOS USARLO DESDE UsuarioService() en vez de ValidacionesService()
    // Boolean validarSession(String id, String session);

}
