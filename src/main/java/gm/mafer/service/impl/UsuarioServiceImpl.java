package gm.mafer.service.impl;

import gm.mafer.model.Usuario;
import gm.mafer.repository.UsuarioRepository;
import gm.mafer.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository; // Inyeccion de un repositorio para usuario desde la interface Repository para traer los usuarios

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) { // Constructor  para UsuarioServiceImpl
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    @Override
    public List<Usuario> findAllUsuariosPorNombre(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosPorNombre = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getNombre().equalsIgnoreCase(nombre)){
                usuariosPorNombre.add(usuarios.get(i));
            }
        }
        return usuariosPorNombre;
    }

    @Override
    public List<Usuario> findAllUsuariosPorApellido(String apellido) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosPorApellido = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getApellido().equalsIgnoreCase(apellido)){
                usuariosPorApellido.add(usuarios.get(i));
            }
        }
        return usuariosPorApellido;
    }

    @Override
    public List<Usuario> findAllUsuariosPorSexo(String sexo) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosPorSexo = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getSexo().equalsIgnoreCase(sexo)){
                usuariosPorSexo.add(usuarios.get(i));
            }
        }
        return usuariosPorSexo;
    }

    @Override
    public List<Usuario> findAllUsuariosPorRol(String rol) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosPorRol = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getRole().equalsIgnoreCase(rol)){
                usuariosPorRol.add(usuarios.get(i));
            }
        }
        return usuariosPorRol;
    }

    @Override
    public List<Usuario> findAllUsuariosPorEmail(String email) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosPorEmail = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getEmail().equalsIgnoreCase(email)){
                usuariosPorEmail.add(usuarios.get(i));
            }
        }
        return usuariosPorEmail;
    }

    @Override
    public List<Usuario> findAllUsuariosPorAsesoriaVencida(LocalDate fechaActual) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosPorAsesoriaVencida = new ArrayList<>();
        System.out.println("Fecha actual dentro de funcion: " + fechaActual);
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getFechaInicioAsesoria()!=null){
                Period period = Period.between(fechaActual, usuarios.get(i).getFechaFinAsesoria());
                int year = period.getYears();
                int month = period.getMonths();
                int day = period.getDays();
                if(year<=0 && month<=0 && day<=0){
                    usuariosPorAsesoriaVencida.add(usuarios.get(i));
                    System.out.println("usuario agregado: " + usuarios.get(i).getUsername());
                }
            }

        }
        for(int i = 0; i < usuariosPorAsesoriaVencida.size(); i++){
            System.out.println("usuario: " + i + " " + usuariosPorAsesoriaVencida.get(i).getUsername());
        }
        System.out.println(usuariosPorAsesoriaVencida);
        return usuariosPorAsesoriaVencida;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUsuarioById(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        return usuario;
    }

    @Override
    public Usuario findUsuarioByUsername(String username) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario usuario = new Usuario();
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getUsername().equalsIgnoreCase(username)){
                usuario = usuarios.get(i);
            }
        }
            return usuario;
    }

    @Override
    public Boolean findUsuarioByUsernameExist(String username) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        boolean bandera = false;

        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getUsername().equalsIgnoreCase(username)){
                bandera = true;
            }
        }
        return bandera;
    }

    @Override
    public Boolean findUsuarioByEmailExist(String email) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        Boolean bandera = false;

        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getEmail().equalsIgnoreCase(email)){
                bandera = true;
            }
        }
        return bandera;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    @Override
    public Boolean deleteUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        if(usuario != null) {
            usuarioRepository.delete(usuario);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Double calcularIMC(Double peso, Double altura) {
        Double imc = peso / (Math.pow(altura, 2));
        return imc;
    }

    @Override
    public Integer calcularEdad(LocalDate birthDate) {
        Period period = Period.between(birthDate, java.time.LocalDate.now());
        Integer edad = period.getYears();
        return edad;
    }

    @Override
    public LocalDate calcularFinAsesoria(LocalDate fechaInicioAsesoria) {
        LocalDate inicio = fechaInicioAsesoria;
        Period periodo = Period.ofDays(30);
        LocalDate vencimientoAsesoria = inicio.plus(periodo);
        return vencimientoAsesoria;
    }


}