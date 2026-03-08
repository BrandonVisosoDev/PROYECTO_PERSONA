package com.proyecto1.inndata020.repository;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity,Integer> {
    //metodos personalizados, tenemos por medio de palabras clave de JpaRepository
    //buscar los departamentos que tengan menos de 70 metros cuadrados y que precio sea igual
    //que 1200
    public List<DepartamentoEntity> findByM2LessThanAndPrecioIs(Integer m2, Double precio);
    //por medio de Queryes
    @Query(value = "select * from departamento where m2>:m2 and precio<:precio;",nativeQuery = true)
    public List<DepartamentoEntity> menorQueM2Precio(Integer m2, Double precio);

}
