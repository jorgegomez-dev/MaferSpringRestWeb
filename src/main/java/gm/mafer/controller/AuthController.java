package gm.mafer.controller;

import gm.mafer.model.Usuario;
import gm.mafer.repository.UsuarioRepository;
import gm.mafer.service.UsuarioService;
import gm.mafer.service.ValidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/autenticacion")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService; // Creamos un usuarioService para poder usar los servicios de Usuario

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidacionesService validacionesService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        try {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Obtener detalles del usuario autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Imprime y verifica la asignacion de ROLE y Autorithies que tiene userDatails
            System.out.println(userDetails.getAuthorities());
            System.out.println(userDetails);

            // Buscar el usuario en la base de datos (opcional si solo necesitas el ID)
            Usuario usuario = usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            // Verificar el rol del usuario, asignar id de session si es necesario y redirigir a la URL correspondiente
            // Si es ROLE_ADMIN y no tiene session activa
            if (userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) && usuario.getSession() == null) { // aca va igual a como lo pusimos en authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); en la clase CustomUserDetailsService
                usuario.setSession(validacionesService.generarSession()); // generamos un id de session random verificando que no este actualmente en uso
                usuario.setAuthenticated(true);
                usuarioService.actualizarUsuario(usuario); // persistimos el id de session actual y la autenticacion
                System.out.println("Se inicia la sesion y creamos el id de sesion: " + usuario.getSession());
                System.out.println("yendo a admincontrolpanel");
                return ResponseEntity.ok("admincontrolpanel.html?id=" + usuario.getId() + "&session=" + usuario.getSession());

            // Si es ROLE_ADMIN y tiene session activa
            } else if (userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) && usuario.getSession() != null) { // aca va siempre SIN prefijo ROLE
            System.out.println("Se inicia la sesion y recuperamos el id se sesion activa:: " + usuario.getSession());
            System.out.println("yendo a admincontrolpanel");
            return ResponseEntity.ok("admincontrolpanel.html?id=" + usuario.getId() + "&session=" + usuario.getSession());

            // Si es ROLE_USER y no tiene session activa
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_USER")) && usuario.getSession() == null) { // aca va siempre SIN prefijo ROLE, solo hace esto si la sesion es null, es decir si no hay sesion activa
                usuario.setSession(validacionesService.generarSession()); // generamos un id de session random verificando que no este actualmente en uso
                usuario.setAuthenticated(true);
                usuarioService.actualizarUsuario(usuario); // persistimos el id de session actual
                System.out.println("Se inicia la sesion y creamos el id se sesion: " + usuario.getSession());
                System.out.println("yendo a usercontrolpanel");
                return ResponseEntity.ok("usercontrolpanel.html?id=" + usuario.getId() + "&session=" + usuario.getSession());

            // Si es ROLE_USER y tiene session activa
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_USER")) && usuario.getSession() != null) { // aca va siempre SIN prefijo ROLE
                System.out.println("Se inicia la sesion y recuperamos el id se sesion activa: " + usuario.getSession());
                System.out.println("yendo a usercontrolpanel");
                return ResponseEntity.ok("usercontrolpanel.html?id=" + usuario.getId() + "&session=" + usuario.getSession());

            // Si no cumple con las condiciones anteriores, no se permite la autenticacion
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Rol no autorizado");
            }

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }

    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam Long id, @RequestParam Integer session) {
        System.out.println("Id: " + id);

        Usuario usuario = usuarioService.findUsuarioById(id);
        System.out.println("Usuario encontrado: " + usuario);
        System.out.println("Session: " + session + " cerrada.");

        if(Objects.equals(usuario.getSession(), session)){
            usuario.setSession(null);
            usuario.setAuthenticated(false);
            usuarioService.actualizarUsuario(usuario); // Cerramos la sesion actualizando la info del usuario
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validarSession")
    public ResponseEntity<?> validarSession(
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session) {

        // Validar la sesión usando el servicio correspondiente
        if (validacionesService.validarSession(id, session)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
        }
    }

}





