package com.proyecto1.inndata020.service.impl;

import java.util.Optional;
import com.proyecto1.inndata020.entity.PersonaEntity;
import com.proyecto1.inndata020.repository.PersonaRepository;
import com.proyecto1.inndata020.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<PersonaEntity> listarPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public PersonaEntity buscarPorId(Integer id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public PersonaEntity guardarPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public PersonaEntity actualizarPersona(Integer id, PersonaEntity persona) {
        PersonaEntity personaExistente = personaRepository.findById(id).orElse(null);
        if (personaExistente != null) {
            personaExistente.setNombre(persona.getNombre());
            personaExistente.setDireccion(persona.getDireccion());
            personaExistente.setEdad(persona.getEdad());
            personaExistente.setIdDepartamento(persona.getIdDepartamento()); // <-- camelCase
            return personaRepository.save(personaExistente);
        }
        return null;
    }

    @Override
    public PersonaEntity borrarLogico(Integer id) {
        PersonaEntity personaExistente = personaRepository.findById(id).orElse(null);
        if (personaExistente != null) {
            personaExistente.setActivo(false);
            return personaRepository.save(personaExistente);
        }
        return null;
    }

    @Override
    public List<PersonaEntity> obtenerPersonasPorDepartamento(Long idDepartamento) {
        return personaRepository.findByIdDepartamento(idDepartamento);
    }

    @Override
    public List<PersonaEntity> obtenerPersonasPorRangoEdad(int minEdad, int maxEdad) {
        return personaRepository.findByEdadBetween(minEdad, maxEdad);
    }





}