package gm.mafer.service.impl;

import gm.mafer.model.Usuario;
import gm.mafer.repository.UsuarioRepository;
import gm.mafer.service.UsuarioService;
import gm.mafer.service.ValidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class ValidacionesServiceImpl implements ValidacionesService {

    private final int duracionSesionROLE_ADMIN = 3600; // SETEAR EL TIEMPO DE SESION PARA ADMIN QUE QUEREMOS ACA (en segundos)
    private final int duracionSesionROLE_USER = 3600; // SETEAR EL TIEMPO DE SESION PARA USER QUE QUEREMOS ACA (en segundos)
    private final int cantidadDeSesiones = 3000; // SETEAR LA CANTIDAD DE CONEXIONES SIMULTANEAS QUE QUEREMOS PERMITIR ACA

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyeccion de un repositorio para usuario desde la interface Repository para traer los usuarios

    @Autowired
    UsuarioService usuarioService; // Inyeccion de dependencias para poder usar los servicios

    @Override
    public Boolean validarSession(String id, String session) {
        Usuario usuario = usuarioRepository.findById(Long.valueOf(id)).get();
        String username = usuario.getUsername(); // BORRAR variable solo para seguimiento por consola

        LocalTime lastUpdated = LocalTime.from(LocalDateTime.from(usuario.getUpdatedOn()));
        LocalTime nowTime = LocalTime.now();
        System.out.println("lastUpdated: " + lastUpdated.toString());
        System.out.println("nowTime: " + nowTime);
        Duration d1 = Duration.between(lastUpdated, nowTime); // Saco el tiempo entre la ultima actualizacion de accion del usuario
        Duration d2 = Duration.ofSeconds(duracionSesionROLE_USER); // Establezco el tiempo de session con variable FINAL default para USER

        if(Objects.equals(usuario.getRole(), "ROLE_ADMIN")){
            d2 = Duration.ofSeconds(duracionSesionROLE_ADMIN); // Si el usuario resulta ser ADMIN, se le asigna un tiempo mayor
        }
        System.out.println("Tiempo Sesion actual: " + d1 + ", Tiempo Limite: " + d2); // BORRAR Seguimiento del tiempo por consola
        if(d1.compareTo(d2) > 0){
            usuario.setAuthenticated(false);
            usuarioService.actualizarUsuario(usuario);
            System.out.println("Se vencio la autenticacion...");
        }

        System.out.println("Usuario: " + username); // BORRAR Seguimiento del tiempo por consola
        System.out.println("Autenticando..."); // BORRAR Seguimiento del tiempo por consola

        if (usuario.getSession() != null
                && Objects.equals(usuario.getSession(), Integer.valueOf(session))
                && usuario.isAuthenticated()) {
            // Si es válida, retornamos true
            System.out.println("Sesion valida para: " + usuario.getUsername()); // BORRAR Seguimiento del tiempo por consola
            System.out.println("Sesion id: " + usuario.getSession()); // BORRAR Seguimiento del tiempo por consola
            return true;
        } else {
            System.out.println("Sesion invalida para: " + usuario.getUsername()); // BORRAR Seguimiento del tiempo por consola
            System.out.println("Sesion id: " + usuario.getSession()); // BORRAR Seguimiento del tiempo por consola
            usuario.setSession(null);
            usuarioService.actualizarUsuario(usuario);
            System.out.println("Sesion: " + usuario.getUsername() + " cerrada."); // BORRAR Seguimiento del tiempo por consola
            return false;
        }
    }

    // Este metodo genera un id de sesion random, verificando que no exista en este momento ese mismo id de sesion activa
    @Override
    public int generarSession() {
        // Genera un número de sesión aleatorio entre 1 y 2000
        // Esto es condicion ficticia del servidor, se puede cambiar de acuerdo a los requerimientos reales en produccion
        int numeroSesion = (int)(Math.random() * cantidadDeSesiones + 1); // cantidadDeSesiones es Final

        // Obtener la lista de usuarios con sesiones existentes
        List<Usuario> usuarios = usuarioRepository.findAll();

        // Verificar si el número de sesión ya está en uso
        for (int i = 0; i < usuarios.size(); i++) {
            // Si algún usuario tiene el mismo número de sesión, se vuelve a generar
            if (Objects.equals(usuarios.get(i).getSession(), numeroSesion)) {
                // Llamada recursiva para generar un nuevo número
                return generarSession();
            }
        }

        // Si el número no está en uso por otra sesion, se retorna para ser asignado al usuario
        return numeroSesion;
    }

}
