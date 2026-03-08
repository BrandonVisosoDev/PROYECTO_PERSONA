package com.proyecto1.inndata020.model;

import lombok.Data;

@Data
public class DepartamentoDtoRequest {

    private Integer id;
    private Integer m2;
    private Double precio;
    private Boolean activo;
}