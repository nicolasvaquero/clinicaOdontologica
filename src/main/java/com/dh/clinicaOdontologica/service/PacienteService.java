package com.dh.clinicaOdontologica.service;


import com.dh.clinicaOdontologica.dao.IDao;
import com.dh.clinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardar(Paciente p){
        return pacienteIDao.guardar(p);
    }
    public Paciente buscar(int id){
        return pacienteIDao.buscar(id);
    }
    public List<Paciente> buscarTodos(){
        return pacienteIDao.buscarTodos();
    }
    public void eliminar(int id){
         pacienteIDao.eliminar(id);
    }
    
    public void actualizar(Paciente p){
        pacienteIDao.actualizar(p);
    }
}
