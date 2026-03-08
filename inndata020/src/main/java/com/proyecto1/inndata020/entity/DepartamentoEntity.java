package com.proyecto1.inndata020.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departamento")
//Lombok sirve para reducir codigo con annotation
@NoArgsConstructor //Constructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
@Data //getter y setter

public class DepartamentoEntity {
    @Id //Es una annotation que nos dice cual atributo es una llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "m2")
    private Integer m2;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "activo")
    private Boolean activo=true;
}
