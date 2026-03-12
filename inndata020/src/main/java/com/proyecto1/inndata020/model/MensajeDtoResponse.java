package com.proyecto1.inndata020.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDtoResponse {
    private String mensaje;
    private Boolean exito;
}