package com.example.ProyectoIntegrador.model;

import com.example.ProyectoIntegrador.service.ProductoService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.persistence.*;

@Data
@NoArgsConstructor // agrega constructor vacio(que usa hibernate)
@AllArgsConstructor //genera un constructor con todos los atributos (automatico de lombok)
@Entity
@Table(name = "puntuaciones")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long puntuaciones_id;

    @Column(length = 1)
    private int puntuacion;

    @Column(name="usuarios_id")
    private Long usuarios_id;

    @Column(name= "productos_id")
    private Long productosId;
}


