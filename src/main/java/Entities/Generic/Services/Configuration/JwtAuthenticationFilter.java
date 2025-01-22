package Entities.Generic.Services.Configuration;

import Host.Services.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    public JwtAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Extraer el token JWT de la cabecera Authorization
        String token = extractToken(request);
        
        if (token != null && authService.validateToken(token)) {
            // Si el token es válido, se puede establecer el usuario en el contexto de seguridad
            String username = authService.getUsernameFromToken(token);
            SecurityContextHolder.getContext().setAuthentication(authService.getAuthentication(username));
        }
        
        filterChain.doFilter(request, response);  // Continuar con la cadena de filtros
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
