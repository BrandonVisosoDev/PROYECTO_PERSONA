package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.model.DepartamentoDtoRequest;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import com.proyecto1.inndata020.service.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService implements IDepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public List<DepartamentoEntity> readAll() {
        List<DepartamentoEntity> info = departamentoRepository.findAll();
        List<DepartamentoEntity> filtrada = new ArrayList<>();
        for (DepartamentoEntity d : info) {
            if (Boolean.TRUE.equals(d.getActivo())) {
                filtrada.add(d);
            }
        }
        return filtrada;
    }

    @Override
    public Optional<DepartamentoEntity> readById(Integer id) {
        return departamentoRepository.findById(id);
    }

    @Override
    public String create(DepartamentoDtoRequest departamento) {
        try {
            DepartamentoEntity departamento1 = new DepartamentoEntity();
            departamento1.setM2(departamento.getM2());
            departamento1.setPrecio(departamento.getPrecio()); // ⚠️ Faltaba
            departamento1.setActivo(true); // ⚠️ Faltaba, siempre inicia activo
            departamentoRepository.save(departamento1);
            return "Departamento creado de manera exitosa";
        } catch (Exception e){

            return "Error al crear el departamento: " + e.getMessage();

        }
    }

    @Override
    public String updateById(Integer id, DepartamentoDtoRequest departamentoNuevo) {
        try {


            Optional<DepartamentoEntity> departamentoBuscado = departamentoRepository.findById(id);
            if (departamentoBuscado.isPresent()) {
                DepartamentoEntity departamento = departamentoBuscado.get();
                departamento.setM2(departamentoNuevo.getM2());
                departamento.setPrecio(departamentoNuevo.getPrecio());
                departamentoRepository.save(departamento);
                return "Departamento actualizado";
            } else {
                return "Departamento no encontrado";
            }

        } catch (Exception e){

            return "Error al actualizar el departamento: " + e.getMessage();
        }
    }

    @Override
    public String deleteById(Integer id) {
        try{
        Optional<DepartamentoEntity> departamentoABorrar = departamentoRepository.findById(id);
        if (departamentoABorrar.isPresent()) {
            DepartamentoEntity departamento = departamentoABorrar.get();
            departamento.setActivo(false);
            departamentoRepository.save(departamento);
            return "Departamento borrado";
        } else {
            return "No existe tal departamento";
        }
        } catch (Exception e){
            return "Error al borrar el departamento: " + e.getMessage();
        }
    }

    public List<DepartamentoEntity> m2AndPrecio(Integer m2, Double precio) {
        return departamentoRepository.findByM2LessThanAndPrecioIs(m2, precio);
    }

    public List<DepartamentoEntity> m2PrecioQ(Integer m2, Double precio) {
        return departamentoRepository.menorQueM2Precio(m2, precio);
    }

}