package gm.mafer.config;

import gm.mafer.model.Usuario;
import gm.mafer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Asignar authorities (permisos) según el rol en base de datos
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (usuario.getRole().equals("ROLE_ADMIN")) { // aca va ROLE_siempre que en base de datos sea ROLE_ y que usemos .hasRole en el filter, sino va sin ROLE_
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // este va siempre
            authorities.add(new SimpleGrantedAuthority("read"));
            authorities.add(new SimpleGrantedAuthority("write"));
        } else if (usuario.getRole().equals("ROLE_USER")) { // aca va ROLE_siempre
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("read"));

        }

        // Crear un objeto UserDetails con authorities
        return new org.springframework.security.core.userdetails
                .User(usuario.getUsername(), usuario.getPassword(), authorities);
    }
}

