package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Caracteristica;
import com.example.ProyectoIntegrador.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

}
