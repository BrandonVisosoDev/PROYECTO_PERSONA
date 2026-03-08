package com.proyecto1.inndata020.model;

import lombok.Data;

@Data
public class PersonaDtoResponse {

    private String nombre;
    private String direccion;
    private Integer edad;
    private Boolean activo;
    private DepartamentoDtoRequest departamento;
}