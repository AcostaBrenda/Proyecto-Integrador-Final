package com.example.ProyectoIntegrador.DTO;

import com.example.ProyectoIntegrador.model.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CiudadDTO {

    private Long ciudades_id;
    private String nombre;
    private String nombre_pais;
    private List<Producto> productos;
}
