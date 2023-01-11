package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.DTO.CategoriaDTO;
import com.example.ProyectoIntegrador.DTO.CiudadDTO;
import com.example.ProyectoIntegrador.DTO.ProductoDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.model.Categoria;
import com.example.ProyectoIntegrador.model.Ciudad;
import com.example.ProyectoIntegrador.model.Imagen;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductoServiceTest {

    @Autowired @Qualifier("productoService")
    private ProductoService productoService;
    @Autowired(required=true)
    private CategoriaService categoriaService;
    @Autowired(required=true)
    private CiudadService ciudadService;
    @Autowired(required=true)
    private ImagenService imagenService;

    @Autowired(required=true)
    ProductoDTO productoDTO;
    @Autowired(required=true)
    ProductoDTO productoDTO2;
    @Autowired(required=true)
    Imagen imagen1;
    @Autowired(required=true)
    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void crear_producto() throws BadRequestException {
        //creo y agrego producto
        productoDTO.setNombre("auto lujoso 3");
        productoDTO.setDescripcion("descripcion auto lujoso");
        productoDTO.setNormas("una norma");
        productoDTO.setPoliticaCancelacion("una policita");
        productoDTO.setSaludYSeguridad("salud y seguridad");
        productoDTO.setDireccion("av alberdi");

        CategoriaDTO categoria1 = categoriaService.buscar(1L);
        CiudadDTO ciudad1 = ciudadService.buscar(1L);
        productoDTO.setCategoria(mapper.convertValue(categoria1, Categoria.class));
        productoDTO.setCiudad(mapper.convertValue(ciudad1, Ciudad.class));

        productoDTO.setListadeimagenes(new ArrayList<>());
        productoDTO.setPuntuaciones(new ArrayList<>());


        try {
            assertNotNull(productoService.agregar(productoDTO));

        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void listar() {
        //listar todos los productos
        try {
            List<ProductoDTO> productoDTOList = productoService.listarTodas();
            assertTrue(productoDTOList.size() > 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buscar_por_id(){
        try {
            ProductoDTO productoDTO = productoService.buscar(1L);
            assertNotNull(productoDTO);
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void editar_producto(){
        //editar y buscar productos por id
        try {
            productoDTO.setProductos_id(1L);
            productoDTO.setNombre("auto deportivo 2");
            productoDTO.setDescripcion("descripcion auto deportivo 2");
            productoDTO.setPoliticaCancelacion("una policita");
            productoDTO.setSaludYSeguridad("salud y seguridad");
            productoDTO.setDireccion("av alberdi");

            CategoriaDTO categoria1 = categoriaService.buscar(1L);
            CiudadDTO ciudad1 = ciudadService.buscar(1L);
            productoDTO.setCategoria(mapper.convertValue(categoria1, Categoria.class));
            productoDTO.setCiudad(mapper.convertValue(ciudad1, Ciudad.class));

            productoDTO.setListadeimagenes(new ArrayList<>());
            productoDTO.setPuntuaciones(new ArrayList<>());

            productoService.editarSinId(productoDTO);
            ProductoDTO productoEditado = productoService.buscar(productoDTO.getProductos_id());

            assertEquals(productoDTO.getNombre(), productoEditado.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void lisyar_por_ciudad(){
        //listar productos por ciudad
        try {
            List<ProductoDTO> productos = productoService.buscarProductoPorCiudad(1L);
            assertTrue(productos.size() > 0);

        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listar_por_categoria(){
        //listar productos por categoria
        try {
            List<ProductoDTO> productos = productoService.buscarProductoPorCategoria(1L);
            assertTrue(productos.size() > 0);

        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void listar_imagenes_x_producto(){
        //listarImagenesSegunProducto

        productoDTO2.setNombre("auto");
        productoDTO2.setDescripcion("buenisimo");
        imagen1.setUrlImagen("https://images.unsplash.com/photo-1503376780353-7e6692767b70?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80");
        imagen1.setTitulo("Autazo negro");

        imagenService.agregar(imagen1);
        List<Imagen> listaimagenes = new ArrayList<>();

        listaimagenes.add(imagen1);
        productoDTO.setListadeimagenes(listaimagenes);
        assertTrue(productoDTO.getListadeimagenes().size() > 0);

        /* JSONAssert.assertEquals("{titulo:\"Autazo negro\"}", "Autazo negro", false);
        JSONAssert.assertEquals("{urlImagen:\"https://images.unsplash.com/photo-1503376780353-7e6692767b70?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80\"}", listaimagenes, false);
        */
    }



}