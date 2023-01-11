package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    @Query(value = "SELECT * FROM ciudades where ciudades_id = :id", nativeQuery = true)
    Ciudad findCityById(@Param("id") Long id);

}
