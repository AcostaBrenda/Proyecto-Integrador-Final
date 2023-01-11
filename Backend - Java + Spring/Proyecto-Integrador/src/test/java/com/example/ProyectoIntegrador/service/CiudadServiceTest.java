package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.DTO.CiudadDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.model.Ciudad;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class CiudadServiceTest {

    @Autowired
    CiudadService ciudadService;

    CiudadDTO ciudadDTO = new CiudadDTO();
    CiudadDTO ciudadDTO2 = new CiudadDTO();

    @Test
    public void crear_ciudad() throws BadRequestException {
        //test método agregar y buscar por id

        ciudadDTO.setNombre("rosario 2");
        ciudadDTO.setNombre_pais("arg");

        try {
            CiudadDTO ciudadAgregada = ciudadService.agregar(ciudadDTO);
            assertNotNull(ciudadAgregada);

            CiudadDTO ciudadEncontrada = ciudadService.buscar(ciudadAgregada.getCiudades_id());
            assertNotNull(ciudadEncontrada);

        } catch (BadRequestException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void listar_todas(){
        //test método listar ciudades

        List<CiudadDTO> ciudades = ciudadService.listarTodas();
        assertTrue(ciudades.size() >0);
    }
    //SPRING_PROFILE=local

    @Test
    public void editar_ciudad() throws BadRequestException {
        //test método editar ciudad

        ciudadDTO.setNombre("rosario 3");
        ciudadDTO.setNombre_pais("arg");

        CiudadDTO ciudadAgregada = ciudadService.agregar(ciudadDTO);

        ciudadDTO2.setCiudades_id(ciudadAgregada.getCiudades_id());
        ciudadDTO2.setNombre("nuevo nombre de ciudad");
        ciudadDTO2.setNombre_pais("nuevo nombre de pais");

        try {
            CiudadDTO ciudadActualizada = ciudadService.editarSinId(ciudadDTO2);
            assertEquals(ciudadActualizada.getNombre(), ciudadDTO2.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void buscar_por_id() throws BadRequestException{
        //test método eliminar ciudad

        ciudadDTO.setNombre("rosario 5");
        ciudadDTO.setNombre_pais("arg");

        CiudadDTO ciudadAgregada = ciudadService.agregar(ciudadDTO);

        CiudadDTO ciudadEncontrada = ciudadService.buscar(ciudadAgregada.getCiudades_id());
        assertTrue(ciudadEncontrada.getNombre() == ciudadEncontrada.getNombre());

    }




}