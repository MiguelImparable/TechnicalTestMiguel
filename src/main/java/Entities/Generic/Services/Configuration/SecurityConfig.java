package Entities.Generic.Services.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;

    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }

    // Configurar las reglas de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/lists/authenticate").permitAll()  // Permite el endpoint de autenticación sin token
                    .antMatchers(HttpMethod.GET, "/lists/**").authenticated()  // Protege los GET
                    .antMatchers(HttpMethod.POST, "/lists/**").authenticated()  // Protege los POST
                    .antMatchers(HttpMethod.DELETE, "/lists/**").authenticated()  // Protege los DELETE
                    .anyRequest().authenticated() // Protege cualquier otra solicitud
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), authService)) // Filtro para autenticación JWT
                .requiresChannel()
                .anyRequest()
                .requiresSecure() // Forzar el uso de HTTPS
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Para encriptar contraseñas
    }

    // Configura un UserDetailsService si es necesario
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Aquí buscarías el usuario en la base de datos
            throw new UsernameNotFoundException("Usuario no encontrado");
        };
    }
}