package com.example.ProyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@NoArgsConstructor // agrega constructor vacio(que usa hibernate)
@AllArgsConstructor //genera un constructor con todos los atributos (automatico de lombok)
@Entity
@Table(name = "ciudades")
public class Ciudad {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ciudades_id;

    @Getter @Setter
    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    @Getter @Setter
    @Column(length = 50, nullable = false)
    private String nombre_pais;

    @Getter @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<Producto>productos;

}
