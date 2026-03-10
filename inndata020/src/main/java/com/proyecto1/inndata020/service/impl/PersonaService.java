package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.model.PersonaDtoRequest;
import com.proyecto1.inndata020.model.PersonaDtoResponse;
import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.entity.PersonaEntity;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import com.proyecto1.inndata020.repository.PersonaRepository;
import com.proyecto1.inndata020.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonaService implements IPersonaService {

    private final PersonaRepository personaRepository;
    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository, DepartamentoRepository departamentoRepository) {
        this.personaRepository = personaRepository;
        this.departamentoRepository = departamentoRepository;
    }

    private PersonaDtoResponse toResponse(PersonaEntity persona) {
        PersonaDtoResponse dto = new PersonaDtoResponse();
        dto.setNombre(persona.getNombre());
        dto.setDireccion(persona.getDireccion());
        dto.setEdad(persona.getEdad());

        return dto;
    }

    @Override
    public List<PersonaDtoResponse> listarPersonas() {
        return personaRepository.findAll()
                .stream()
                .filter(PersonaEntity::getActivo)
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Optional<PersonaDtoResponse> buscarPorId(Integer id) {
        return personaRepository.findById(id)
                .filter(PersonaEntity::getActivo) // ✅ solo si está activo
                .map(this::toResponse);
    }

    @Override
    public String guardarPersona(PersonaDtoRequest request) {
        DepartamentoEntity departamento = departamentoRepository
                .findById(request.getIdDepartamento())
                .orElse(null);

        if (departamento == null) return "Departamento no encontrado";

        PersonaEntity persona = new PersonaEntity();
        persona.setNombre(request.getNombre());
        persona.setDireccion(request.getDireccion());
        persona.setEdad(request.getEdad());
        persona.setActivo(request.getActivo());
        persona.setDepartamento(departamento);

        personaRepository.save(persona);
        return "Persona creada exitosamente";
    }

    @Override
    public String actualizarPersona(Integer id, PersonaDtoRequest request) {
        PersonaEntity persona = personaRepository.findById(id).orElse(null);
        if (persona == null) return "Persona no encontrada";

        DepartamentoEntity departamento = departamentoRepository
                .findById(request.getIdDepartamento())
                .orElse(null);
        if (departamento == null) return "Departamento no encontrado";

        persona.setNombre(request.getNombre());
        persona.setDireccion(request.getDireccion());
        persona.setEdad(request.getEdad());
        persona.setActivo(request.getActivo());
        persona.setDepartamento(departamento);

        personaRepository.save(persona);
        return "Persona actualizada exitosamente";
    }

    @Override
    public String borrarLogico(Integer id) {
        PersonaEntity persona = personaRepository.findById(id).orElse(null);
        if (persona == null) return "Persona no encontrada";

        persona.setActivo(false);
        personaRepository.save(persona);
        return "Persona desactivada exitosamente";
    }

    @Override
    public List<PersonaDtoResponse> obtenerPersonasPorDepartamento(Integer idDepartamento) {
        return personaRepository.findByDepartamento_Id(idDepartamento)
                .stream()
                .filter(PersonaEntity::getActivo) // ✅ solo activos
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<PersonaDtoResponse> obtenerPersonasPorRangoEdad(Integer minEdad, Integer maxEdad) {
        return personaRepository.findByEdadBetween(minEdad, maxEdad)
                .stream()
                .filter(PersonaEntity::getActivo) // ✅ solo activos
                .map(this::toResponse)
                .toList();
    }
}