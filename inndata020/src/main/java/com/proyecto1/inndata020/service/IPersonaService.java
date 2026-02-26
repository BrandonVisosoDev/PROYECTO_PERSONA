package com.proyecto1.inndata020.service;

import com.proyecto1.inndata020.entity.PersonaEntity;
import com.proyecto1.inndata020.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    List<PersonaEntity> listarPersonas();

    PersonaEntity buscarPorId(Integer id);

    PersonaEntity guardarPersona(PersonaEntity persona);

    PersonaEntity actualizarPersona(Integer id, PersonaEntity persona);

    void eliminarPersona(Integer id);

}
