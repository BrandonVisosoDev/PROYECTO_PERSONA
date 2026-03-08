package com.proyecto1.inndata020.model;

import lombok.Data;

@Data
public class PersonaDtoRequest {

    private String nombre;
    private String direccion;
    private Integer edad;
    private Integer id_departamento;
    private Boolean activo;
}