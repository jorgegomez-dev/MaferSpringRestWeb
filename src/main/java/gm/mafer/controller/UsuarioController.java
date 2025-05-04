package gm.mafer.controller;

import gm.mafer.model.Usuario;
import gm.mafer.repository.UsuarioRepository;
import gm.mafer.service.UsuarioService;
import gm.mafer.service.ValidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

// En esta clase definiremos los endpoints de Usuario
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository; // Creamos un usuarioRepository
    @Autowired
    private UsuarioService usuarioService; // Creamos un usuarioService para poder usar los servicios de Usuario

    @Autowired
    public UsuarioController(UsuarioService usuarioService) { // Constructor de UsuarioController
        this.usuarioService = usuarioService;
    }

    @Autowired
    public ValidacionesService validacionesService; // Creo el servicio para poder llamar a los metodos

    @Autowired
    private PasswordEncoder passwordEncoder;


    // ResponseEntity con el signo <?> reemplaza el objeto que devuelve el metodo y ademas puede enviar un mensaje http con
    // los codigos de protocolo para el manejo de peticiones y respuesta cliente - servidor


    @GetMapping("/listarUsuarios")
    public ResponseEntity<?> listarUsuarios(
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session) {

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findAllUsuarios(), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mostrarUsuarioId")
    public ResponseEntity<?> mostrarUsuarioById (
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session){

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findUsuarioById(Long.valueOf(id)), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/buscarUsuarioId")
    public ResponseEntity<?> buscarUsuarioById (
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "userId", required = true) String userId){

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findUsuarioById(Long.valueOf(userId)), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarUsuariosPorNombre")
    public ResponseEntity<?> listarUsuariosPorNombre (
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "nombre", required = true) String nombre){

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findAllUsuariosPorNombre(nombre), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/listarUsuariosPorApellido")
    public ResponseEntity<?> listarUsuariosPorApellido (
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "apellido", required = true) String apellido){

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findAllUsuariosPorApellido(apellido), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/listarUsuariosPorSexo")
    public ResponseEntity<?> listarUsuariosPorSexo (
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "sexo", required = true) String sexo){

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findAllUsuariosPorSexo(sexo), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarUsuariosPorRol")
    public ResponseEntity<?> listarUsuariosPorRol (
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "rol", required = true) String rol){

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findAllUsuariosPorRol(rol), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarUsuariosPorEmail")
    public ResponseEntity<?> listarUsuariosPorEmail (
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "email", required = true) String email){

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(usuarioService.findAllUsuariosPorEmail(email), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listarUsuariosPorAsesoriaVencida")
    public ResponseEntity<?> listarUsuariosPorAsesoriaVencida(
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestBody Map<String, String> fechaMap) {

        try {
            // Verifica si el usuario y la sesión son válidos
            if (!validacionesService.validarSession(id, session)) {
                return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
            } else {
                // Extraer la fecha del cuerpo del request
                String fechaString = fechaMap.get("fecha");
                System.out.println("Fecha recibida: " + fechaString); // Log para depuración

               // Formatear la fecha
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta al formato que envíes
                LocalDate fechaActual = LocalDate.parse(fechaString, formatter);
                //LocalDate fechaActual = LocalDate.now();
                return new ResponseEntity<>(usuarioService.findAllUsuariosPorAsesoriaVencida(fechaActual), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    // Ente entrypoint no requiere autenticacion ya que el usuario se autoregistra en el sistema
    @PostMapping("/crearUsuario")
    public ResponseEntity<?>crearUsuario(@RequestBody Usuario usuario) {

        // Vamos a validar que al intentar crear el usuario que nos pasa el cliente, no exista en base de
        // datos ni el username ni el email, ya que deben ser unicos
        if (!usuarioService.findUsuarioByUsernameExist(usuario.getUsername()) && !usuarioService.findUsuarioByEmailExist(usuario.getEmail())) {
            usuarioService.crearUsuario(usuario);
            Integer edad = usuarioService.calcularEdad(usuario.getBirthdate());
            usuario.setEdad(edad);
            if (usuario.getId() == 1) {
                usuario.setRole("ROLE_ADMIN");
                usuario.setUsername("admin");
                usuario.setPassword("1234");
            } else {
                usuario.setRole("ROLE_USER");
            }
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // encriptar password
            if (usuario.getPeso() != null && usuario.getAltura() != null) {
                double imc = usuarioService.calcularIMC(usuario.getPeso(), usuario.getAltura());
                usuario.setImc(imc);
                usuarioService.actualizarUsuario(usuario);
            }
            return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido crear el Usuario");
        }
    }


    // Este entrypoint si requiere autenticacion para crear un usuario, ya que se hace con permisos de ADMIN
    @PostMapping("/crearUsuarioAdmin")
    public ResponseEntity<?>crearUsuarioAdmin(
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestBody Usuario usuario) {

        // Verifica si el usuario y la sesión son válidos
        if (!validacionesService.validarSession(id, session)) {
            return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);

        } else {
            // El codigo aca si esta autorizado
            // Vamos a validar que al intentar crear el usuario que nos pasa el cliente, no exista en base de
            // datos ni el username ni el email, ya que deben ser unicos }
            if (!usuarioService.findUsuarioByUsernameExist(usuario.getUsername()) && !usuarioService.findUsuarioByEmailExist(usuario.getEmail())) {
                usuarioService.crearUsuario(usuario);
                Integer edad = usuarioService.calcularEdad(usuario.getBirthdate());
                usuario.setEdad(edad);
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // encriptar password
                if (usuario.getPeso() != null && usuario.getAltura() != null) {
                    double imc = usuarioService.calcularIMC(usuario.getPeso(), usuario.getAltura());
                    usuario.setImc(imc);
                    usuarioService.actualizarUsuario(usuario);
                }
                System.out.println("Usuario creado desde panel de Administrador"); // NOTA BORRAR
                return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido crear el Usuario");
            }
        }
    }

    @PutMapping("/updateUsuario")
    public ResponseEntity<?> updateUsuario(
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestBody Usuario usuario){

        // Verifica si el usuario y la sesión son válidos
        if (!validacionesService.validarSession(id, session)) {
            return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);

        } else {
            // El codigo aca si esta autorizado
            // Vamos a validar que al intentar crear el usuario que nos pasa el cliente, no exista en base de
            // datos ni el username ni el email, ya que deben ser unicos
            Usuario updateUsuario = usuarioService.findUsuarioById(Long.valueOf(id));
            if(updateUsuario != null) {
                updateUsuario.setNombre(usuario.getNombre());
                updateUsuario.setApellido(usuario.getApellido());
                updateUsuario.setPeso(usuario.getPeso());
                updateUsuario.setAltura(usuario.getAltura());
                updateUsuario.setEmail(usuario.getEmail());
                updateUsuario.setTelefono(usuario.getTelefono());
                updateUsuario.setUsername(usuario.getUsername());
                if(usuario.getPassword()!=null){
                    updateUsuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // encriptar password
                }
                updateUsuario.setSexo(usuario.getSexo());
                updateUsuario.setUrlfotoperfil(usuario.getUrlfotoperfil());
                double imc = usuarioService.calcularIMC(usuario.getPeso(), usuario.getAltura());
                updateUsuario.setImc(imc);
                usuarioService.actualizarUsuario(updateUsuario);

                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }


    @PutMapping("/updateUsuarioAdmin")
    public ResponseEntity<?> updateUsuarioAdmin(
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "userId", required = true) String userId,
            @RequestBody Usuario usuario){

        // Verifica si el usuario y la sesión son válidos
        if (!validacionesService.validarSession(id, session)) {
            return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);

        } else {
            // El codigo aca si esta autorizado
            // Vamos a validar que al intentar crear el usuario que nos pasa el cliente, no exista en base de
            // datos ni el username ni el email, ya que deben ser unicos
            Usuario updateUsuario = usuarioService.findUsuarioById(Long.valueOf(userId));
            if(updateUsuario != null) {
                updateUsuario.setNombre(usuario.getNombre());
                updateUsuario.setApellido(usuario.getApellido());
                updateUsuario.setPeso(usuario.getPeso());
                updateUsuario.setAltura(usuario.getAltura());
                updateUsuario.setEmail(usuario.getEmail());
                updateUsuario.setTelefono(usuario.getTelefono());
                updateUsuario.setUsername(usuario.getUsername());
                updateUsuario.setRole(usuario.getRole());
                if(usuario.getPassword()!=null){
                    updateUsuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // encriptar password
                }
                updateUsuario.setSexo(usuario.getSexo());
                updateUsuario.setIdRutinaActual(usuario.getIdRutinaActual());
                updateUsuario.setFechaInicioRutina(usuario.getFechaInicioRutina());
                updateUsuario.setFechaInicioAsesoria(usuario.getFechaInicioAsesoria());
                updateUsuario.setUrlfotoperfil(usuario.getUrlfotoperfil());
                updateUsuario.setIdAsesoriaActual(usuario.getIdAsesoriaActual());
                double imc = usuarioService.calcularIMC(usuario.getPeso(), usuario.getAltura());
                updateUsuario.setImc(imc);
                if(updateUsuario.getFechaInicioAsesoria()!= null){
                    LocalDate vencimiento = usuarioService.calcularFinAsesoria(updateUsuario.getFechaInicioAsesoria());
                    updateUsuario.setFechaFinAsesoria(vencimiento);
                }
                usuarioService.actualizarUsuario(updateUsuario);

                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
            }

    }

    @DeleteMapping("/deleteUsuario")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "id", required = true) String id,
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "userId", required = true) Long userId){

        // Verifica si el usuario y la sesión son válidos
        if (!validacionesService.validarSession(id, session)) {
            return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);

        } else {
            // El codigo aca si esta autorizado
            // Vamos a validar que al intentar crear el usuario que nos pasa el cliente, no exista en base de
            // datos ni el username ni el email, ya que deben ser unicos
            if(usuarioService.deleteUsuario(userId)){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();

        }
    }
}
