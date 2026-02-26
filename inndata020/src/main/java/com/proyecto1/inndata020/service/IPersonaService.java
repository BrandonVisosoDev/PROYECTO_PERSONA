package com.proyecto1.inndata020.service;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.repository.DepartamentoRepository;

import java.util.List;
import java.util.Optional;

public interface IDepartamentoService {
    //Métodos read
    public List<DepartamentoEntity> readAll();
    public Optional<DepartamentoEntity> readById(Integer id);
    //Metodo create
    public String create(DepartamentoEntity departamento);
    public String updateById(Integer id, DepartamentoEntity departamentoNuevo);
}
