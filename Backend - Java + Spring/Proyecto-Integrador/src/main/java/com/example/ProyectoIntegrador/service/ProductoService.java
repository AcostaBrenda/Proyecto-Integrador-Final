package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.DTO.FechasDTO;
import com.example.ProyectoIntegrador.DTO.ProductoDTO;

import com.example.ProyectoIntegrador.model.*;
import com.example.ProyectoIntegrador.repository.*;

import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.model.Categoria;
import com.example.ProyectoIntegrador.model.Producto;
import com.example.ProyectoIntegrador.repository.CategoriaRepository;
import com.example.ProyectoIntegrador.model.Puntuacion;
import com.example.ProyectoIntegrador.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IGenericaService<ProductoDTO, Long>{

    final static Logger logger = Logger.getLogger(ProductoService.class);

    @Autowired
    private PuntuacionService puntuacionService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CategoriaService categoriaService ;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private ImagenService imagenService;

    @Override
    public ProductoDTO buscar(Long id) throws BadRequestException {
        Optional<Producto> productoEncontrado = productoRepository.findById(id);
        ProductoDTO productoEncontradoDto = mapper.convertValue(productoEncontrado, ProductoDTO.class);
        if (productoEncontradoDto == null) {
            throw new BadRequestException("No existe producto con ese id");
        } else {
            return productoEncontradoDto;
        }
    }

    public List<ProductoDTO> buscarProductoPorCiudad(Long id) throws BadRequestException {
        List<ProductoDTO> productoDTOList = new ArrayList<>();
        Ciudad ciudad = ciudadRepository.findCityById(id);
        if(ciudad == null){
            logger.error("no existe ciudad con ese id");
            throw new BadRequestException("no existe ciudad con ese id");
        }
        List<Producto> productosEcontrados = productoRepository.findProductsByCity(id);
        if(productosEcontrados ==null || productosEcontrados.size() ==0){
            logger.error("no existen productos en esa ciudad");
            throw new BadRequestException("no existen productos en esa ciudad");
        }
        for (Producto producto : productosEcontrados) {
            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            productoDTOList.add(productoDTO);
        }
        return productoDTOList;
    }

    public List<ProductoDTO> buscarProductoPorCategoria(Long id) throws BadRequestException {
        List<ProductoDTO> productoDTOList = new ArrayList<>();
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isEmpty() || categoria.get().getCategorias_id() == null){
            logger.error("no existe categoria con ese id");
            throw new BadRequestException("no existe categoria con ese id");
        }
        List<Producto> productosEcontrados = productoRepository.findProductsByCategory(id);
        if(productosEcontrados ==null || productosEcontrados.size() ==0){
            logger.error("no existen productos en esa categoria");
            throw new BadRequestException("no existen productos en esa categoria");
        }
        for (Producto producto : productosEcontrados) {
            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            productoDTOList.add(productoDTO);
        }
        return productoDTOList;
    }


    @Override
    public ProductoDTO agregar(ProductoDTO productoDTO) throws BadRequestException{
        if(productoDTO == null){
            logger.error("Los datos ingresados son nulos");
            throw new BadRequestException("Los datos ingresados son nulos");
        }
        if(productoDTO.getProductos_id() != null){
            throw new BadRequestException("No es posible guardar un producto con un id previamente asignado");
        }
        else{
            Producto productoNuevo = mapper.convertValue(productoDTO, Producto.class);

            /**   bloque de agregado de caracteristicas NUEVAS y/o ya existentes, tambien las retorna con id luego de pasar por caracteristicaService (y la BD)**/

            logger.info("aqui estan los productos filtrados de la lista, los no repetidos "+ productoNuevo.getCaracteristicas().stream().collect(Collectors.toSet()));

            productoNuevo.setCaracteristicas(caracteristicaService.agregarNueva(productoNuevo.getCaracteristicas().stream().collect(Collectors.toSet())));

            /**-------------------------------------**/

            if(productoDTO.getListadereservas().isEmpty()){
                productoDTO.setListadereservas(null);
            }

            /**------------------ Bloque agregado de imágenes -------------------**/

            productoNuevo.setListadeimagenes(imagenService.agregarNueva(productoDTO.getListadeimagenes()));

            /**-------------------------------------**/

            logger.info("PRODUCTO : " + productoNuevo);

            productoRepository.save(productoNuevo);
            logger.info("Se agregó un nuevo producto al sistema");
            return mapper.convertValue(productoNuevo, ProductoDTO.class);
        }
    }

    @Override
    public List<ProductoDTO> listarTodas() {
        List<ProductoDTO> productoDTOList = new ArrayList<>();
        List<Producto> productoList = productoRepository.findAll();
        for (Producto producto : productoList) {

            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            productoDTOList.add(productoDTO);
        }
        logger.info("Devolviendo el listado de productos existentes");
        return productoDTOList;
    }

    @Override
    public ProductoDTO editar(ProductoDTO productoDTO, Long id) throws BadRequestException {
        Optional<Producto> producto1 = productoRepository.findById(id);
        if(producto1.isPresent()){
            Producto p = producto1.get();
            p.setNombre(productoDTO.getNombre());
            p.setDescripcion(productoDTO.getDescripcion());
            p.setNormas(productoDTO.getNormas());
            p.setSaludYSeguridad(productoDTO.getSaludYSeguridad());
            p.setPoliticaCancelacion(productoDTO.getPoliticaCancelacion());
            p.setDireccion(productoDTO.getDireccion());
            /**   bloque de agregado de caracteristicas NUEVAS y/o ya existentes, tambien las retorna con id luego de pasar por caracteristicaService (y la BD)**/

            if (productoDTO.getCaracteristicas().isEmpty()){
                productoDTO.setCaracteristicas(null);  //para que no rompa si agregan una lista vacia,por si no quieren actualizar ninguna
            }
            else {
                p.setCaracteristicas(caracteristicaService.agregarNueva(productoDTO.getCaracteristicas().stream().collect(Collectors.toSet())));
            }
            /**-------------------------------------**/

            /**------------------ Bloque agregado de imágenes -------------------**/
            if (productoDTO.getListadeimagenes().isEmpty()){
                productoDTO.setListadeimagenes(null);  //para que no rompa si agregan una lista vacia,por si no quieren actualizar ninguna
            }
            else {
                p.setListadeimagenes(imagenService.agregarNueva(productoDTO.getListadeimagenes()));
            }
            if(productoDTO.getListadereservas().isEmpty()){
                productoDTO.setListadereservas(null);
            }
            logger.info("producto bd " + p);
            /**   bloque de agregado de puntuacion  **/
            if (productoDTO.getPuntuaciones().isEmpty()){
                productoDTO.setPuntuaciones(null);      //si al actualizar, se olvidan o no quieren colocar una puntuacion, el programa no  rompe, agrega un null
            }
            else{
                Puntuacion puntuacion1 = productoDTO.getPuntuaciones().get(0);
                puntuacionService.agregar(puntuacion1);
            }
            ProductoDTO respuesta= mapper.convertValue(productoRepository.save(producto1.get()),ProductoDTO.class);
            respuesta.setPuntuaciones(puntuacionRepository.findByProductosId(productoDTO.getProductos_id()));

            /**-------------------------------------**/

            logger.info("El producto se actualizó correctamente");
            return productoDTO;
        }else {
            logger.error("no se pudo encontrar el producto buscado o no existe");
            throw new BadRequestException("No se pudo encontrar el producto buscado o no existe");
        }
    }

    public ProductoDTO editarSinId(ProductoDTO productoDTO) throws BadRequestException {
        Optional<Producto> producto1 = productoRepository.findById(productoDTO.getProductos_id());
        if(producto1.isPresent()){
            Producto p = producto1.get();
            p.setNombre(productoDTO.getNombre());
            p.setDescripcion(productoDTO.getDescripcion());
            p.setNormas(productoDTO.getNormas());
            p.setSaludYSeguridad(productoDTO.getSaludYSeguridad());
            p.setPoliticaCancelacion(productoDTO.getPoliticaCancelacion());
            p.setDireccion(productoDTO.getDireccion());
            /**   bloque de agregado de caracteristicas NUEVAS y/o ya existentes, tambien las retorna con id luego de pasar por caracteristicaService (y la BD)**/

            if (productoDTO.getCaracteristicas().isEmpty()){
                productoDTO.setCaracteristicas(null);  //para que no rompa si agregan una lista vacia,por si no quieren actualizar ninguna
            }
            else {
            p.setCaracteristicas(caracteristicaService.agregarNueva(productoDTO.getCaracteristicas().stream().collect(Collectors.toSet())));
            }
            /**-------------------------------------**/

            /**------------------ Bloque agregado de imágenes -------------------**/
            if (productoDTO.getListadeimagenes().isEmpty()){
                productoDTO.setListadeimagenes(null);  //para que no rompa si agregan una lista vacia,por si no quieren actualizar ninguna
            }
            else {
                p.setListadeimagenes(imagenService.agregarNueva(productoDTO.getListadeimagenes()));
            }

            if(productoDTO.getListadereservas().isEmpty()){
                productoDTO.setListadereservas(null);
            }
            logger.info("producto bd " + p);

            /**bloque de agregado de puntuacion**/
            if (productoDTO.getPuntuaciones().isEmpty()){
                productoDTO.setPuntuaciones(null);      //si al actualizar, se olvidan o no quieren colocar una puntuacion, el programa no rompe, agrega un null
            }
            else{
                Puntuacion puntuacion1 = productoDTO.getPuntuaciones().get(0);
                logger.info("puntuacion front " + puntuacion1);
                logger.info("respuesta de la bd : " + puntuacionService.agregar(puntuacion1));
            }
            ProductoDTO respuesta= mapper.convertValue(productoRepository.save(p),ProductoDTO.class);
            respuesta.setPuntuaciones(puntuacionRepository.findByProductosId(productoDTO.getProductos_id()));

            /**-------------------------------------**/

            logger.info("El producto se actualizó correctamente");
            return productoDTO;
        }else {
            logger.error("no se pudo encontrar el producto buscado o no existe");
            throw new BadRequestException("No se pudo encontrar el producto buscado o no existe");
        }
    }


    public List<Imagen> buscarPorId(Long id) throws BadRequestException {
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto == null){
            logger.error("no existe producto con ese id");
            throw new BadRequestException("no existe producto con ese id");
        }

        List<Imagen> imagenesencontradas = producto.get().getListadeimagenes();
        if(imagenesencontradas == null || imagenesencontradas.size() ==0){
            logger.error("no existen imagenes en ese producto");
            throw new BadRequestException("no existen imagenes en ese producto");
        }
        return imagenesencontradas;
    }

    public List<ProductoDTO> buscarPorFechas(FechasDTO fechasDTO) throws BadRequestException{
        LocalDate fechaInicial = LocalDate.parse(fechasDTO.getFechaInicial());
        LocalDate fechaFinal = LocalDate.parse(fechasDTO.getFechaFinal());
        if(fechaInicial.isAfter(fechaFinal) || (fechaInicial.isBefore(LocalDate.now()))) {
            throw new BadRequestException("El rango de fechas ingresado no es válido");
        }
        List<Producto> productosDisponibles = productoRepository.filterProductsByDates(fechaInicial, fechaFinal);
        List<ProductoDTO> productosDisponiblesDTO = new ArrayList<>();
        for (Producto producto : productosDisponibles) {
            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            productosDisponiblesDTO.add(productoDTO);
        }
        return  productosDisponiblesDTO;
    }

    public List<ProductoDTO> filtrarPorCiudadYFechas(Long idCiudad, FechasDTO fechasDTO) throws BadRequestException {
        LocalDate fechaInicial = LocalDate.parse(fechasDTO.getFechaInicial());
        LocalDate fechaFinal = LocalDate.parse(fechasDTO.getFechaFinal());
        if(fechaInicial.isAfter(fechaFinal) || (fechaInicial.isBefore(LocalDate.now()))) {
            throw new BadRequestException("El rango de fechas ingresado no es válido");
        }
        List<Producto> disponiblesPorCiudadYFechas = productoRepository.filterProductsByCityAndDates(idCiudad, fechaInicial, fechaFinal);
        List<ProductoDTO> disponiblesPorCiudadYFechasDTO = new ArrayList<>();
        for (Producto producto : disponiblesPorCiudadYFechas) {
            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            disponiblesPorCiudadYFechasDTO.add(productoDTO);
        }
        return disponiblesPorCiudadYFechasDTO;
    }

    @Override
    public void eliminar(Long id) throws BadRequestException {
        logger.info("Eliminando un producto");
        productoRepository.deleteById(id);
    }


}
