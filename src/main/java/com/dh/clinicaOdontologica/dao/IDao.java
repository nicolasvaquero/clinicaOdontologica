package com.dh.clinicaOdontologica.dao;

import java.util.List;

public interface IDao<T> {

    public T guardar(T t);
    public T buscar(int id);
    public void eliminar(int id);
    public void actualizar(T t);
    public List<T> buscarTodos();

}
