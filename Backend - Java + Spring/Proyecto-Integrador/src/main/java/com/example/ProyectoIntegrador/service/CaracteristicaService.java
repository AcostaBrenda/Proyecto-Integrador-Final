package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.DTO.CaracteristicaDTO;
import com.example.ProyectoIntegrador.DTO.ProductoDTO;
import com.example.ProyectoIntegrador.model.Caracteristica;
import com.example.ProyectoIntegrador.model.Puntuacion;
import com.example.ProyectoIntegrador.repository.CaracteristicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaracteristicaService implements IGenericaService<CaracteristicaDTO, Long>{

    final static Logger logger = Logger.getLogger(CaracteristicaService.class);

    @Autowired(required = true)
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public CaracteristicaDTO buscar(Long id) {
        Caracteristica caracteristicaA = caracteristicaRepository.findById(id).orElse(null);
        logger.info("Caracteristica encontrada");
        return mapper.convertValue(caracteristicaA,CaracteristicaDTO.class);
    }

    @Override
    public CaracteristicaDTO agregar(CaracteristicaDTO caracteristicaDTO) {
        Caracteristica caracteristicaNueva = new Caracteristica();
        caracteristicaNueva.setNombre(caracteristicaDTO.getNombre());
        caracteristicaNueva.setIcono(caracteristicaDTO.getIcono());
        logger.info("Caracteristica agregada");
        return mapper.convertValue(caracteristicaRepository.save(caracteristicaNueva),CaracteristicaDTO.class);
    }

    @Override
    public List<CaracteristicaDTO> listarTodas() {
        List<CaracteristicaDTO> listaCaracteristicas = mapper.convertValue(caracteristicaRepository.findAll(), List.class);
        logger.info("Listado de caracteristicas existentes");
        return listaCaracteristicas;
    }

    @Override
    public CaracteristicaDTO editar(CaracteristicaDTO caracteristicaDTO, Long id) {
        Caracteristica caracteristica= mapper.convertValue(this.buscar(id),Caracteristica.class);
        if (caracteristica!= null){
            caracteristica.setNombre(caracteristicaDTO.getNombre());
            caracteristica.setIcono(caracteristicaDTO.getIcono());
            logger.info("Caracteristica actualizada correctamente");
            return mapper.convertValue(caracteristicaRepository.save(caracteristica),CaracteristicaDTO .class);
        }else{
            logger.error("la caracteristica buscada no existe");
            return null;
        }
    }

    @Override
    public void eliminar(Long id) {
        logger.info("Eliminando caracteristica");
        caracteristicaRepository.deleteById(id);
        }

/**Segun lo solicitado en sprint 4, agrego metodo para agregar una nueva caracteristica a traves de agregar un producto(no con su propio endpoint),
y tambien si ya esta guardada en BD solo relacionarla con el nuevo producto para no duplicarse los registros **/

    public List<Caracteristica> agregarNueva(Set<Caracteristica> caracteristicaList) {
        List<Caracteristica> responseList = new ArrayList<>();

        for (Caracteristica c : caracteristicaList){
            if(this.caracteristicaRepository.findByNombre(c.getNombre()) == null){
                responseList.add(this.caracteristicaRepository.save(c));

            }else {
                responseList.add(this.caracteristicaRepository.findByNombre(c.getNombre()));
            }
        }
        logger.info("LISTA DE CARACTERISTICAS RESTANTE : " + responseList);
        return responseList;
    }
}
/** ----------------------------------------------------------------------------------------- **/