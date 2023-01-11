package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.DTO.CategoriaDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping()
    public ResponseEntity agregar(@RequestBody CategoriaDTO categoriaDTO) throws BadRequestException{
        return ResponseEntity.ok(categoriaService.agregar(categoriaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(categoriaService.buscar(id));
    }

    @GetMapping()
    public ResponseEntity listarTodas(){
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @PutMapping()
    public ResponseEntity editar(@RequestBody CategoriaDTO categoriaDTO) throws BadRequestException {
        return ResponseEntity.ok(categoriaService.editarSinId(categoriaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws BadRequestException {
        ResponseEntity<String> response = null;
        if (categoriaService.buscar(id) != null) {
            categoriaService.eliminar(id);
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
