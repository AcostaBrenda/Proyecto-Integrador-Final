package com.example.ProyectoIntegrador.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor // agrega constructor vacio(que usa hibernate)
@AllArgsConstructor //genera un constructor con todos los atributos (automatico de lombok)
@Entity
@Table(name = "imagenes")
public class Imagen{


    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagenes_id")
    private Long imagenes_id;

    @Getter @Setter
    @Column(length = 100, nullable = false)
    private String titulo;

    @Getter @Setter
    @Column(length = 300, nullable = false)
    private String urlImagen;

    @Getter @Setter
    @Column(name = "productos_id")
    private Long productos_id;

}
