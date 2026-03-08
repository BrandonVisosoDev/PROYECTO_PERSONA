package com.proyecto1.inndata020.service;

import com.proyecto1.inndata020.model.PersonaDtoRequest;
import com.proyecto1.inndata020.model.PersonaDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    // Read
    List<PersonaDtoResponse> listarPersonas();
    Optional<PersonaDtoResponse> buscarPorId(Integer id);

    // Create / Update / Delete
    String guardarPersona(PersonaDtoRequest persona);
    String actualizarPersona(Integer id, PersonaDtoRequest persona);
    String borrarLogico(Integer id);

    // Métodos personalizados
    List<PersonaDtoResponse> obtenerPersonasPorDepartamento(Integer idDepartamento);
    List<PersonaDtoResponse> obtenerPersonasPorRangoEdad(Integer minEdad, Integer maxEdad);
}