package com.example.ProyectoIntegrador.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor // agrega constructor vacio(que usa hibernate)
@AllArgsConstructor //genera un constructor con todos los atributos (automatico de lombok)
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productos_id;

    @Getter @Setter
    @Column(length = 50, nullable = false)
    private String nombre;

    @Getter @Setter
    @Column(length = 255)
    private String normas;

    @Getter @Setter
    @Column(length = 255)
    private String saludYSeguridad;

    @Getter @Setter
    @Column(length = 255)
    private String politicaCancelacion;

    @Getter @Setter
    @Column(nullable = false)
    private String descripcion;

    @Getter @Setter
    @Column(nullable = false)
    private String direccion;

    @Getter @Setter
    @ManyToMany
    @JoinTable(name ="productos_has_caracteristicas", joinColumns = @JoinColumn(name = "productos_id"),
            inverseJoinColumns = @JoinColumn(name ="caracteristicas_id"))              //unidireccional
    private List <Caracteristica> caracteristicas;


    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productos_id")
    private List<Puntuacion> puntuaciones;     //unidireccional

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productos_id")
    private List<Imagen> listadeimagenes;       //unidireccional

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productos_id")
    private List<Reserva> listadereservas;       //unidireccional

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "ciudades_id", nullable = false)
    private Ciudad ciudad;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "categorias_id")
    private Categoria categoria;

}
