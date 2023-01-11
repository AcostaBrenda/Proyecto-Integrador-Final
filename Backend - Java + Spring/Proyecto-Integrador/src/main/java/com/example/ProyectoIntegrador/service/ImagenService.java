package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Imagen;
import com.example.ProyectoIntegrador.repository.ImagenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImagenService implements IGenericaService<Imagen, Long>{


    final static Logger logger = Logger.getLogger(ImagenService.class);

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ObjectMapper mapper;

    /** Método de agregado de imágenes mediante el endpoint de creación de productos **/

    public List<Imagen> agregarNueva(List<Imagen> imagenesList) {
        List<Imagen> responseList = new ArrayList<>();

        for (Imagen i : imagenesList){
            responseList.add(this.imagenRepository.save(i));
        }
        logger.info("LISTA DE IMAGENES RESTANTE : " + responseList);
        return responseList;
    }

    @Override
    public Imagen buscar(Long aLong) {
        return null;
    }

    @Override
    public Imagen agregar(Imagen Imagen) {
        return null;
    }

    @Override
    public List<Imagen> listarTodas() {
      return null;
    }

    @Override
    public Imagen editar(Imagen Imagen, Long id) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
    }
}
