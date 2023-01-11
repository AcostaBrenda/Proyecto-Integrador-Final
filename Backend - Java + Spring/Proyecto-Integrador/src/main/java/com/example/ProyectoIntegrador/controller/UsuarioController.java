package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.DTO.UsuarioDTO;
import com.example.ProyectoIntegrador.exceptions.AuthenticationException;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.security.DTO.AuthenticationRequestDTO;
import com.example.ProyectoIntegrador.security.DTO.AuthenticationResponseDTO;
import com.example.ProyectoIntegrador.security.service.UserService;
import com.example.ProyectoIntegrador.service.UsuarioService;
import com.example.ProyectoIntegrador.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity<?> agregar(@RequestBody UsuarioDTO usuarioDTO)throws BadRequestException{
        UsuarioDTO usuarioCreadoDTO = usuarioService.agregar(usuarioDTO);
        String jwt = getJwt(usuarioCreadoDTO.getEmail());
        return new ResponseEntity(new AuthenticationResponseDTO(jwt),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authDTO) throws AuthenticationException, BadRequestException {
        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new AuthenticationException("Nombre usuario o contraseña incorrecta");
        }
        /*
        UserDetails userDetails = userDetailsService.loadUserByUsername(authDTO.getEmail());
        // Pasamos el nombre al Claims del JWT para evitar una nueva llamada al API
        UsuarioDTO userDTO = usuarioService.buscarPorEmail(authDTO.getEmail());
        String nombreCompleto = userDTO.getNombre().concat(" "+userDTO.getApellido());
        String jwt = jwtTokenUtil.generateToken(userDetails,nombreCompleto );
           */
        String jwt = getJwt(authDTO.getEmail());
        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }

    private String getJwt(String email) throws BadRequestException{
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        // Pasamos el nombre al Claims del JWT para evitar una nueva llamada al API
        UsuarioDTO userDTO = usuarioService.buscarPorEmail(email);
        String nombreCompleto = userDTO.getNombre().concat(" "+userDTO.getApellido());
        String jwt = jwtTokenUtil.generateToken(userDetails,userDTO );
        return jwt;
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
