package Entities.Generic.Services.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final String secretKey = "mySecretKey"; // Cambia esto a un valor seguro
    private final long expirationTime = 86400000; // 1 día en milisegundos
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Autentica un usuario con nombre de usuario y contraseña.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return Token JWT generado tras autenticación exitosa.
     */
    public String authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        if (authentication.isAuthenticated()) {
            return generateToken(username);
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

    /**
     * Genera un token JWT.
     * 
     * @param username Nombre de usuario.
     * @return Token JWT.
     */
    private String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    /**
     * Valida un token JWT.
     * 
     * @param token Token JWT.
     * @return `true` si el token es válido, de lo contrario `false`.
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrae el nombre de usuario de un token JWT.
     * 
     * @param token Token JWT.
     * @return Nombre de usuario.
     */
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }
}