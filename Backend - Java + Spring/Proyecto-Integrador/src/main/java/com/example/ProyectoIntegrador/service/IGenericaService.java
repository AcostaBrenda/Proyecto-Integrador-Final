package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.exceptions.BadRequestException;

import java.util.List;

public interface IGenericaService<T, K> {

    T buscar(K k) throws BadRequestException; //busca y retorna por id
    T agregar(T t) throws BadRequestException;
    List<T> listarTodas();
    T editar(T t, K k) throws BadRequestException;
    void eliminar(K k) throws BadRequestException;

}
