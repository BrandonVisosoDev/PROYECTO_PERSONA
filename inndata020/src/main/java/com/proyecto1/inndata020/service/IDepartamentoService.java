package com.proyecto1.inndata020.service;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.model.DepartamentoDtoRequest;

import java.util.List;
import java.util.Optional;

public interface IDepartamentoService {

    // Métodos read
    List<DepartamentoEntity> readAll();
    Optional<DepartamentoEntity> readById(Integer id);

    // Métodos create/update/delete
    String create(DepartamentoDtoRequest departamento);
    String updateById(Integer id, DepartamentoDtoRequest departamento);
    String deleteById(Integer id);

    // Métodos personalizados

    List<DepartamentoEntity> m2AndPrecio(Integer m2, Double precio);
    List<DepartamentoEntity> m2PrecioQ(Integer m2, Double precio);
}