package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.DTO.CaracteristicaDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService caracteristicaService;

    @PostMapping()
    public ResponseEntity agregar(@RequestBody CaracteristicaDTO caracteristicaDTO){
        return ResponseEntity.ok(caracteristicaService.agregar(caracteristicaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        return ResponseEntity.ok(caracteristicaService.buscar(id));
    }


    @GetMapping()
    public ResponseEntity listarTodas(){
        return ResponseEntity.ok(caracteristicaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity editar(@RequestBody CaracteristicaDTO caracteristicaDTO, @PathVariable Long id){
        return ResponseEntity.ok(caracteristicaService.editar(caracteristicaDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws BadRequestException {
        ResponseEntity<String> response = null;
        if (caracteristicaService.buscar(id) != null) {
            caracteristicaService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            throw new BadRequestException("No existe caracteristica con ese id");
        }
        return response;
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
