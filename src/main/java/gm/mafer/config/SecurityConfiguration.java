package gm.mafer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

// Endpoint Level authorization

// --> Matcher
//  1. AnyRequest (Cualquier peticion)
//  2. RequestMatchers (Determina si cumple con los requisitos en dependencia de la url)
//  3. RequestMatchers whit HttpMethod (igual que la anterior pero tambien podemos discriminar por peticion http

// --> Authorization rules (definidas por los matchers)
//  1. PermitAll (todos tienen acceso al recurso)
//  2. DenyAll (nadie tiene acceso al recurso)
//  3. Authenticated (solo tiene acceso al recurso los usuarios que tiene username y password valido)
//  4. HasRole (solo tiene acceso al recurso al ROLE que se le pasa, por ej: .hasRole("ADMIN")
//  5. HasAuthority (solo tiene acceso al recurso el usuario que tenga como authority la que se pasa como parametro, por ej: .hasAuthority("read")
//  6. Access (SpEL) - Spring Expression Language (para filtros mas complejos)


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF si es necesario
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)) // Usar sesiones
                    .authorizeHttpRequests(authorize -> authorize

                                    //.requestMatchers("/admincontrolpanel.html/{id:[0-9]+}").hasAuthority("ADMIN")
                                    //.requestMatchers("/usercontrolpanel.html/{id:[0-9]+}").hasRole("USER")
                                    //.requestMatchers("/login.html", "/api/autenticacion/login", "/registrarse.html", "/api/usuarios/crearUsuario").permitAll()
                                    .requestMatchers("/**").permitAll()
                                    .anyRequest().authenticated() // Proteger las demás rutas
                    )

                    //.rememberMe(rememberMe -> rememberMe.key("AbcdEfghIjklmNopQrsTuvXyz_0123456789"))
                    .rememberMe(withDefaults())
                    .logout(logout -> logout.logoutUrl("/signout").permitAll())
                    .build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }


    }
}
