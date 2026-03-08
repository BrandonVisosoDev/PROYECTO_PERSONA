package com.proyecto1.inndata020.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// LOOMBOK

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona") // columna en la BD
    private Integer idPersona;   // atributo en camelCase

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "id_departamento") // columna en la BD
    private Integer idDepartamento;   // atributo en camelCase

    @Column(name = "activo")
    private Boolean activo = true;

}