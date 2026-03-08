package com.proyecto1.inndata020.feign;

// LOOMBOK

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data



public class mockPersona {

        private String nombre;
        private String direccion;
        private Integer edad;
        private Integer id_departamento;
        private Boolean activo;

}
