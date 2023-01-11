package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.DTO.CiudadDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @PostMapping()
    public ResponseEntity agregar(@RequestBody CiudadDTO ciudadDTO) throws BadRequestException{
        return ResponseEntity.ok(ciudadService.agregar(ciudadDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(ciudadService.buscar(id));
    }

    @GetMapping()
    public ResponseEntity listarTodas(){
        return ResponseEntity.ok(ciudadService.listarTodas());
    }

    @PutMapping()
    public ResponseEntity editar(@RequestBody CiudadDTO ciudadDTO) throws BadRequestException {
        return ResponseEntity.ok(ciudadService.editarSinId(ciudadDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws BadRequestException {
        ResponseEntity<String> response = null;
        if (ciudadService.buscar(id) != null) {
            ciudadService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            throw new BadRequestException("No existe ciudad con ese id");
        }
        return response;
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
