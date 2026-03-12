package com.proyecto1.inndata020.service;

import com.proyecto1.inndata020.model.MensajeDtoResponse;
import com.proyecto1.inndata020.model.PersonaDtoRequest;
import com.proyecto1.inndata020.model.PersonaDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    // Read
    List<PersonaDtoResponse> listarPersonas();
    Optional<PersonaDtoResponse> buscarPorId(Integer id);

    // Create / Update / Delete
    MensajeDtoResponse guardarPersona(PersonaDtoRequest persona);
    MensajeDtoResponse actualizarPersona(Integer id, PersonaDtoRequest persona);
    MensajeDtoResponse borrarLogico(Integer id);

    // Métodos personalizados
    List<PersonaDtoResponse> obtenerPersonasPorDepartamento(Integer idDepartamento);
    List<PersonaDtoResponse> obtenerPersonasPorRangoEdad(Integer minEdad, Integer maxEdad);

    // Métodos con QUERY
    List<PersonaDtoResponse> buscarPorNombre(String nombre);
    List<PersonaDtoResponse> obtenerPersonasPorEstado(Boolean activo);

}