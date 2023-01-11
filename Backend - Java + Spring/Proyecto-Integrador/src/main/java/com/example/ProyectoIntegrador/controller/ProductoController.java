package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.DTO.FechasDTO;
import com.example.ProyectoIntegrador.DTO.ProductoDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.service.ProductoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    final static Logger logger = Logger.getLogger(ProductoService.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity crearProducto(@RequestBody ProductoDTO productoDTO) throws BadRequestException{
        return new ResponseEntity<ProductoDTO>(productoService.agregar(productoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity listarTodos(){
        return ResponseEntity.ok(productoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(productoService.buscar(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping()
    public ResponseEntity editarSinId(@RequestBody ProductoDTO productoDTO) throws BadRequestException {
        return ResponseEntity.ok(productoService.editarSinId(productoDTO));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws BadRequestException {
        ResponseEntity<String> response = null;
        if (productoService.buscar(id) != null) {
            productoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            throw new BadRequestException("No existe ciudad con ese id");
        }
        return response;
    }

    @GetMapping("/{id}/imagenes")
    public ResponseEntity buscarPorId(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @GetMapping("/buscarPorCategoria/{id}")
    public ResponseEntity<List<ProductoDTO>> buscarProductoPorCategoria(@PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(productoService.buscarProductoPorCategoria(id));
    }

    @GetMapping("/buscarPorCiudad/{id}")
    public ResponseEntity<List<ProductoDTO>> buscarProductoPorCiudad(@PathVariable Long id) throws BadRequestException{
        return ResponseEntity.ok(productoService.buscarProductoPorCiudad(id));
    }

    @PostMapping("/buscarPorFechas")
    public ResponseEntity<List<ProductoDTO>> buscarPorFechas(@RequestBody FechasDTO fechasDTO) throws BadRequestException{
        return ResponseEntity.ok(productoService.buscarPorFechas(fechasDTO));
    }

    @PostMapping("/buscarPorFechasYCiudad/{idCiudad}")
    public ResponseEntity<List<ProductoDTO>> buscarPorFechasYCiudad(@PathVariable Long idCiudad, @RequestBody FechasDTO fechasDTO) throws BadRequestException{
        return ResponseEntity.ok(productoService.filtrarPorCiudadYFechas(idCiudad, fechasDTO));
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
