package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Rol;
import com.example.ProyectoIntegrador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombre(String nombre);
}
