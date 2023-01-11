package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.DTO.ReservaDTO;
import com.example.ProyectoIntegrador.DTO.UsuarioDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.service.ReservaService;
import com.example.ProyectoIntegrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity listarTodos(){
        return ResponseEntity.ok(reservaService.listarTodas());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping()
    public ResponseEntity agregar(Authentication authentication, @RequestBody ReservaDTO reservaDTO)throws BadRequestException {

        // Obtengo el email del usuario autenticado
        final String email = authentication.getName();

        // Obtengo el UsuarioDTO con ese email
        UsuarioDTO usuarioDTO = usuarioService.buscarPorEmail(email);

        // Seteo el ID de ese usuario  dentro de reservaDTO
        reservaDTO.setUsuariosId(usuarioDTO.getUsuarios_id());

        // Guardo la reserva
        return ResponseEntity.ok(reservaService.agregar(reservaDTO));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/buscarPorIdProducto/{id}")
    public ResponseEntity<List<ReservaDTO>> buscarReservaPorIdProducto(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(reservaService.buscarReservaPorIdProducto(id));
    }

    @GetMapping("/buscarPorIdUsuario/{id}")
    public ResponseEntity<List<ReservaDTO>> buscarReservaPorIdUsuario(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(reservaService.buscarReservaPorIdUsuario(id));
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
