package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Puntuacion;
import com.example.ProyectoIntegrador.repository.PuntuacionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntuacionService implements IGenericaService<Puntuacion, Long> {

    final static Logger logger = Logger.getLogger(PuntuacionService.class);

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    /** esto tampoco lo usamos aun **/
    @Override
    public Puntuacion buscar(Long id) {
        Puntuacion puntuacion = puntuacionRepository.findById(id).orElse(null);
        return puntuacion;
    }

    /**en este metodo hay que modificar la linea de usuario, cuando tengamos usuario completo(no lo piden aun)
     * por eso esta en null provisoriamente, este si lo usamos**/
    @Override
    public Puntuacion agregar(Puntuacion puntuacion) {
        logger.info("en agregar puntuacion " + puntuacion);
        Puntuacion p= puntuacionRepository.save(puntuacion);
        logger.info("respuesta de la bd " + p);
        return p;
    }

    /**} modificar este metodo si lo piden a futuro, para listar puntuaciones segun producto, tampoco lo usamos en este sprint **/
    @Override
    public List<Puntuacion> listarTodas() {
        List<Puntuacion> p = puntuacionRepository.findAll();
        return p;
    }

    /** esto tampoco lo usamos aun,ya modifique la linea de usuario **/
    @Override
    public Puntuacion editar(Puntuacion puntuacion, Long id) {
        if (puntuacion != null) {
            puntuacion.setPuntuacion(puntuacion.getPuntuacion());
            puntuacion.setProductosId(puntuacion.getProductosId());
            puntuacion.setUsuarios_id(puntuacion.getUsuarios_id());
            return puntuacionRepository.save(puntuacion);
        } else {
            return null;
        }
    }

    /** esto tampoco lo usamos aun **/
    @Override
    public void eliminar(Long id) {
        logger.info("eliminando una puntuacion");
        puntuacionRepository.deleteById(id);
    }
}