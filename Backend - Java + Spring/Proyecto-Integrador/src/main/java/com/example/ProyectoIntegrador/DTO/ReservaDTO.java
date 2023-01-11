package com.example.ProyectoIntegrador.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private Long reservas_id;

    private Double horaDeReserva;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;

    private Long productosId;
    private Long usuariosId;

}
