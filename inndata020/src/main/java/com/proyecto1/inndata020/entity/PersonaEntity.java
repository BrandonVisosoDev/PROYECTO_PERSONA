package com.persona.proyectoPersona.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity // Esto le dice que es una entidad;
@Table(name = "persona") // Aqui agregamos el nombre de tabla que viene desde el MySQL;
public class PersonaEntity {

    // Definimos el ID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_persona;

    // Mapeamos cada columna de campo

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "id_departamento")
    private Integer id_departamento;

    // Constructor vacio

    public PersonaEntity() {
    }

    // Getter y Setter

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Integer id_departamento) {
        this.id_departamento = id_departamento;
    }
}
