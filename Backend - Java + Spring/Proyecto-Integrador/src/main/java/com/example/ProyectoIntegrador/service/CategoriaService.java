package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.DTO.CategoriaDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.model.Categoria;
import com.example.ProyectoIntegrador.model.Producto;
import com.example.ProyectoIntegrador.repository.CategoriaRepository;
import com.example.ProyectoIntegrador.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements IGenericaService<CategoriaDTO, Long>{

    final static Logger logger = Logger.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public CategoriaDTO buscar(Long id) throws BadRequestException {
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
        if(categoriaEncontrada.isEmpty() || categoriaEncontrada.get().getCategorias_id() == null){
            throw new BadRequestException("No existe categoria con ese id");
        }
        else {
            return mapper.convertValue(categoriaEncontrada.get(), CategoriaDTO.class);
        }
    }

    @Override
    public CategoriaDTO agregar(CategoriaDTO categoriaDTO) throws BadRequestException {
        if(categoriaDTO == null || categoriaDTO.getTitulo() == null){
            logger.error("Los datos ingresados son nulos");
            throw new BadRequestException("Los datos ingresados son nulos");
        }
        if(categoriaDTO.getCategorias_id() != null){
            throw new BadRequestException("No es posible guardar una categoria con un id previamente asignado");
        }
        else{
            Categoria categoriaNueva = mapper.convertValue(categoriaDTO, Categoria.class);
            categoriaRepository.save(categoriaNueva);
            logger.info("Se agregó una nueva categoria al sistema");
            return mapper.convertValue(categoriaNueva, CategoriaDTO.class);
        }
    }

    @Override
    public List<CategoriaDTO> listarTodas() {
        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
        List<Categoria> categoriaList = categoriaRepository.findAll();
        for (Categoria categoria : categoriaList) {
            CategoriaDTO categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);
            categoriaDTOList.add(categoriaDTO);
            List<Producto> productosXCategoria = productoRepository.findProductsByCategory(categoria.getCategorias_id());
            Integer cantidadProductosXCategoria = productosXCategoria.size();
            categoriaDTO.setCantidadProductos(cantidadProductosXCategoria);
        }
        logger.info("Devolviendo el listado de categorias existentes");
        return categoriaDTOList;
    }


    @Override
    public CategoriaDTO editar(CategoriaDTO categoriaDTO, Long id) throws BadRequestException{
        Optional<Categoria> categoria1 = categoriaRepository.findById(id);
        if(categoria1.isPresent()){
            categoria1.get().setTitulo(categoriaDTO.getTitulo());
            categoria1.get().setDescripcion(categoriaDTO.getDescripcion());
            categoria1.get().setUrl_imagen(categoriaDTO.getUrl_imagen());
            categoriaRepository.save(categoria1.get());
            logger.info("Se actualizó correctamente la categoria");
            return categoriaDTO;
        }else {
            logger.error("no se pudo encontrar la categoria buscada o no existe");
            throw new BadRequestException("No se pudo encontrar la categoria buscada o no existe");
        }
    }

    public CategoriaDTO editarSinId(CategoriaDTO categoriaDTO) throws BadRequestException {
        Optional<Categoria> categoria1 = categoriaRepository.findById(categoriaDTO.getCategorias_id());
        if(categoria1.isPresent()){
            categoria1.get().setTitulo(categoriaDTO.getTitulo());
            categoria1.get().setDescripcion(categoriaDTO.getDescripcion());
            categoria1.get().setUrl_imagen(categoriaDTO.getUrl_imagen());
            categoriaRepository.save(categoria1.get());
            logger.info("Se actualizó correctamente la categoria");
            return categoriaDTO;
        }else {
            logger.error("no se pudo encontrar la categoria buscada o no existe");
            throw new BadRequestException("No se pudo encontrar la categoria buscada o no existe");
        }
    }


    @Override
    public void eliminar(Long id) throws BadRequestException {
        logger.info("Eliminando una categoria");
        categoriaRepository.deleteById(id);
    }

}
