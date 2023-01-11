package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.DTO.CategoriaDTO;
import com.example.ProyectoIntegrador.DTO.CiudadDTO;
import com.example.ProyectoIntegrador.DTO.ProductoDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.model.Ciudad;
import com.example.ProyectoIntegrador.repository.CiudadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CiudadService implements IGenericaService<CiudadDTO, Long>{

    final static Logger logger = Logger.getLogger(CiudadService.class);

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public CiudadDTO buscar(Long id) throws BadRequestException {
        Ciudad ciudadEncontrada = ciudadRepository.findCityById(id);
        if (ciudadEncontrada == null) {
            throw new BadRequestException("No existe ciudad con ese id");
        } else {
            return mapper.convertValue(ciudadEncontrada, CiudadDTO.class);
        }
    }

    @Override
    public CiudadDTO agregar(CiudadDTO ciudadDTO) throws BadRequestException {
        if(ciudadDTO == null || ciudadDTO.getNombre() == null){
            logger.error("Los datos ingresados son nulos");
            throw new BadRequestException("Los datos ingresados son nulos");
        }
        if(ciudadDTO.getCiudades_id() != null){
            throw new BadRequestException("No es posible guardar una ciudad con un id previamente asignado");
        }
        else{
            Ciudad ciudadNueva = mapper.convertValue(ciudadDTO, Ciudad.class);
            ciudadRepository.save(ciudadNueva);
            logger.info("Se agregó una nueva ciudad al sistema");
            return mapper.convertValue(ciudadNueva, CiudadDTO.class);
        }
    }

    @Override
    public List<CiudadDTO> listarTodas() {
        List<CiudadDTO> ciudadDTOList = new ArrayList<>();
        List<Ciudad> ciudadList = ciudadRepository.findAll();
        for (Ciudad ciudad : ciudadList) {
            CiudadDTO ciudadDTO = mapper.convertValue(ciudad, CiudadDTO.class);
            ciudadDTOList.add(ciudadDTO);
        }
        logger.info("Devolviendo el listado de ciudades existentes");
        return ciudadDTOList;
    }


    @Override
    public CiudadDTO editar(CiudadDTO ciudadDTO, Long id) throws BadRequestException {
        Optional<Ciudad> ciudad1 = ciudadRepository.findById(id);
        if(ciudad1.isPresent()){
            ciudad1.get().setNombre(ciudadDTO.getNombre());
            ciudad1.get().setNombre_pais(ciudadDTO.getNombre_pais());
            ciudadRepository.save(ciudad1.get());
            logger.info("Se actualizó correctamente la ciudad");
            return ciudadDTO;
        }else {
            logger.error("no se pudo encontrar la ciudad buscada o no existe en el sistema");
            throw new BadRequestException("No se pudo encontrar la ciudad buscada o no existe en el sistema");
        }
    }

    public CiudadDTO editarSinId(CiudadDTO ciudadDTO) throws BadRequestException {
        Optional<Ciudad> ciudad1 = ciudadRepository.findById(ciudadDTO.getCiudades_id());
        if(ciudad1.isPresent()){
            ciudad1.get().setNombre(ciudadDTO.getNombre());
            ciudad1.get().setNombre_pais(ciudadDTO.getNombre_pais());
            ciudadRepository.save(ciudad1.get());
            logger.info("Se actualizó correctamente la ciudad");
            return ciudadDTO;
        }else {
            logger.error("no se pudo encontrar la ciudad buscada o no existe en el sistema");
            throw new BadRequestException("No se pudo encontrar la ciudad buscada o no existe en el sistema");
        }
    }

    @Override
    public void eliminar(Long id) throws BadRequestException {
        logger.info("Eliminando una ciudad del sistema");
        ciudadRepository.deleteById(id);
    }
}
