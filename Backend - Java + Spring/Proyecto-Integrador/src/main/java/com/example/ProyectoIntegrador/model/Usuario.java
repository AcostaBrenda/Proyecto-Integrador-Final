package com.example.ProyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor // agrega constructor vacio(que usa hibernate)
@AllArgsConstructor //genera un constructor con todos los atributos (automatico de lombok)
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarios_id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rol_id")                                    //unidireccional
    private Rol rol;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarios_id")
    private List<Reserva> listadereservas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarios_id")
    private List<Puntuacion> puntuaciones;                           //unidireccional
}
