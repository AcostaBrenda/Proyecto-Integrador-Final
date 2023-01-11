package com.example.ProyectoIntegrador.DTO;

import com.example.ProyectoIntegrador.model.Puntuacion;
import com.example.ProyectoIntegrador.model.Reserva;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long usuarios_id;

    private String nombre;

    private String apellido;

    private String email;

    private String password;

    private List<Puntuacion> puntuaciones;

    private List<Reserva> listadereservas;
}
