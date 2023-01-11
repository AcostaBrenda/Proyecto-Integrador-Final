package com.example.ProyectoIntegrador.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import com.example.ProyectoIntegrador.DTO.UsuarioDTO;

@Service
public class JwtUtil {

    public String SECRET_key = "secret";

    //Extract user name
    public String extractUserName (String token) {
        return extractClaim (token, Claims::getSubject);
    }
    //Extract expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Extract claim
    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Token generator
    public String generateToken(UserDetails userDetails, UsuarioDTO usuarioDTO) {
        Map<String, Object> claims = new HashMap<>();


        // Agrego al payload nombre y apellido para visualizar en <Avatar/>
        claims.put("nombre", usuarioDTO.getNombre());
        claims.put("apellido", usuarioDTO.getApellido());

        // Agrego al payload el listado de roles del usuario
        claims.put("roles", userDetails.getAuthorities());

        return createToken (claims, userDetails.getUsername());
    }

    //Token creator
    private String createToken(Map <String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_key).compact();
    }

    //Token validation
    public Boolean validateToken (String token, UserDetails userDetails) {
        final String username= extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //Token expiration
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Extract claims
    private Claims extractAllClaims (String token) {
        return Jwts.parser().setSigningKey(SECRET_key).parseClaimsJws(token).getBody();
    }

}

//asociar al controller de usuario

