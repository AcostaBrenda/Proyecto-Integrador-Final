package com.example.ProyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor // agrega constructor vacio(que usa hibernate)
@AllArgsConstructor //genera un constructor con todos los atributos (automatico de lombok)
@Entity
@Table(name = "caracteristicas")
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caracteristicas_id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 255)
    private String icono;

}