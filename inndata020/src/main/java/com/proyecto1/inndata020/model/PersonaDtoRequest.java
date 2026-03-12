package com.proyecto1.inndata020.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonaDtoRequest {

    private String nombre;
    private String direccion;
    private Integer edad;
    private Boolean activo;

    @JsonProperty("id_departamento") // Como se lee el Json
    private Integer idDepartamento; // camelCase
}