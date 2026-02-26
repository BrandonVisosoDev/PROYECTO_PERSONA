package com.persona.proyectoPersona.service.impl;

import com.persona.proyectoPersona.entity.PersonaEntity;
import com.persona.proyectoPersona.repository.PersonaRepository;
import com.persona.proyectoPersona.service.IPersonaService;
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
            personaExistente.setId_departamento(persona.getId_departamento());
            return personaRepository.save(personaExistente);
        }
        return null;
    }

    @Override
    public void eliminarPersona(Integer id) {
        personaRepository.deleteById(id);
    }
}