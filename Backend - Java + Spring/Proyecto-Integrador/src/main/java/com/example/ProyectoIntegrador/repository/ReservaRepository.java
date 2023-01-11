package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    //Spring data para consultar reservas por id de producto.
    List<Reserva> findByProductosId(Long id);

    //Spring data para consultar reservas por id de usuario.
    List<Reserva> findByUsuariosId(Long id);

}
