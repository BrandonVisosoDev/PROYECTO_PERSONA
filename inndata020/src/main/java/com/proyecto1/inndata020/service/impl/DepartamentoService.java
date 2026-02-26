package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import com.proyecto1.inndata020.service.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService implements IDepartamentoService {
    //Inyeccion de dependencia
    @Autowired
    DepartamentoRepository departamentoRepository;
    @Override
    public List<DepartamentoEntity> readAll() {
        return departamentoRepository.findAll();
    }

    @Override
    public Optional<DepartamentoEntity> readById(Integer id) {
        return departamentoRepository.findById(id);
    }

    @Override
    public String create(DepartamentoEntity departamento) {
        departamentoRepository.save(departamento);
        return "Departamento creado de manera exitosa";
    }

    @Override
    public String updateById(Integer id, DepartamentoEntity departamentoNuevo) {
        Optional<DepartamentoEntity> departamentoBuscado=departamentoRepository.findById(id);
        if(departamentoBuscado.isPresent()){
            DepartamentoEntity departamento= departamentoBuscado.get();
            departamento.setM2(departamentoNuevo.getM2());
            departamento.setPrecio(departamentoNuevo.getPrecio());
            departamentoRepository.save(departamento);
            return "Departamento actualizado";
        }else{
            return "Departamento no actualizado porque no ha sido creado";
        }

    }
}
