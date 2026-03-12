package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.model.PersonaDtoRequest;
import com.proyecto1.inndata020.model.PersonaDtoResponse;
import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.entity.PersonaEntity;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import com.proyecto1.inndata020.repository.PersonaRepository;
import com.proyecto1.inndata020.service.IPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.proyecto1.inndata020.model.MensajeDtoResponse;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonaService implements IPersonaService {

    // ✅ Constantes para mensajes repetidos
    private static final String ERROR_BASE_DATOS = "Error de acceso a la base de datos: ";
    private static final String ERROR_INESPERADO = "Error inesperado: ";
    private static final String PERSONA_NO_ENCONTRADA = "Persona no encontrada";

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
        log.info("Obteniendo lista de todas las personas activas");
        List<PersonaDtoResponse> personas = personaRepository.findAll()
                .stream()
                .filter(PersonaEntity::getActivo)
                .map(this::toResponse)
                .toList();
        log.info("Total de personas encontradas: {}", personas.size());
        return personas;
    }

    @Override
    public Optional<PersonaDtoResponse> buscarPorId(Integer id) {
        log.info("Buscando persona con id: {}", id);
        Optional<PersonaDtoResponse> persona = personaRepository.findById(id)
                .filter(PersonaEntity::getActivo)
                .map(this::toResponse);
        if (persona.isPresent()) {
            log.info("Persona encontrada con id: {}", id);
        } else {
            log.warn("No se encontro persona activa con id: {}", id);
        }
        return persona;
    }

    @Override
    public MensajeDtoResponse guardarPersona(PersonaDtoRequest request) {
        log.info("Guardando persona: {}", request.getNombre());
        try {
            DepartamentoEntity departamento = departamentoRepository
                    .findById(request.getIdDepartamento())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

            PersonaEntity persona = new PersonaEntity();
            persona.setNombre(request.getNombre());
            persona.setDireccion(request.getDireccion());
            persona.setEdad(request.getEdad());
            persona.setActivo(request.getActivo());
            persona.setDepartamento(departamento);

            personaRepository.save(persona);
            log.info("Persona guardada exitosamente: {}", request.getNombre());
            return new MensajeDtoResponse("Persona guardada exitosamente", true);
        } catch (DataAccessException e) {
            log.error("Error de base de datos al guardar persona: {}", e.getMessage());
            return new MensajeDtoResponse(ERROR_BASE_DATOS + e.getMessage(), false);
        } catch (Exception e) {
            log.error("Error inesperado al guardar persona: {}", e.getMessage());
            return new MensajeDtoResponse(ERROR_INESPERADO + e.getMessage(), false);
        }
    }

    @Override
    public MensajeDtoResponse actualizarPersona(Integer id, PersonaDtoRequest request) {
        log.info("Actualizando persona con id: {}", id);
        try {
            Optional<PersonaEntity> personaOpt = personaRepository.findById(id);
            if (personaOpt.isEmpty()) {
                log.warn("No se encontró persona con id: {}", id);
                return new MensajeDtoResponse(PERSONA_NO_ENCONTRADA, false);
            }
            PersonaEntity persona = personaOpt.get();

            DepartamentoEntity departamento = departamentoRepository
                    .findById(request.getIdDepartamento())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

            persona.setNombre(request.getNombre());
            persona.setDireccion(request.getDireccion());
            persona.setEdad(request.getEdad());
            persona.setActivo(request.getActivo());
            persona.setDepartamento(departamento);

            personaRepository.save(persona);
            log.info("Persona actualizada exitosamente con id: {}", id);
            return new MensajeDtoResponse("Persona actualizada exitosamente", true);
        } catch (DataAccessException e) {
            log.error("Error de base de datos al actualizar persona con id {}: {}", id, e.getMessage());
            return new MensajeDtoResponse(ERROR_BASE_DATOS + e.getMessage(), false);
        } catch (Exception e) {
            log.error("Error inesperado al actualizar persona con id {}: {}", id, e.getMessage());
            return new MensajeDtoResponse(ERROR_INESPERADO + e.getMessage(), false);
        }
    }

    @Override
    public MensajeDtoResponse borrarLogico(Integer id) {
        log.info("Desactivando persona con id: {}", id);
        try {
            Optional<PersonaEntity> personaOpt = personaRepository.findById(id);
            if (personaOpt.isEmpty()) {
                log.warn("No se encontró persona con id: {}", id);
                return new MensajeDtoResponse(PERSONA_NO_ENCONTRADA, false);
            }
            PersonaEntity persona = personaOpt.get();
            persona.setActivo(false);
            personaRepository.save(persona);
            log.info("Persona desactivada exitosamente con id: {}", id);
            return new MensajeDtoResponse("Persona desactivada exitosamente", true);
        } catch (DataAccessException e) {
            log.error("Error de base de datos al desactivar persona con id {}: {}", id, e.getMessage());
            return new MensajeDtoResponse(ERROR_BASE_DATOS + e.getMessage(), false);
        } catch (Exception e) {
            log.error("Error inesperado al desactivar persona con id {}: {}", id, e.getMessage());
            return new MensajeDtoResponse(ERROR_INESPERADO + e.getMessage(), false);
        }
    }

    // Métodos personalizados

    @Override
    public List<PersonaDtoResponse> obtenerPersonasPorDepartamento(Integer idDepartamento) {
        log.info("Buscando personas del departamento con id: {}", idDepartamento);
        try{
        List<PersonaDtoResponse> personas = personaRepository.findByDepartamento_Id(idDepartamento)
                .stream()
                .filter(PersonaEntity::getActivo)
                .map(this::toResponse)
                .toList();
        log.info("Total de personas encontradas en departamento {}: {}", idDepartamento, personas.size());
        return personas;
    } catch (DataAccessException e) {
        log.error("Error de base de datos al buscar personas por departamento con id {}: {}", idDepartamento, e.getMessage());
        return List.of();
    } catch (Exception e) {
        log.error("Error inesperado al buscar personas por departamento con id {}: {}", idDepartamento, e.getMessage());
        return List.of();
    }
    }

    @Override
    public List<PersonaDtoResponse> obtenerPersonasPorRangoEdad(Integer minEdad, Integer maxEdad){
            log.info("Buscando personas con edad entre {} y {}", minEdad, maxEdad);
            try{
            List<PersonaDtoResponse> personas = personaRepository.findByEdadBetween(minEdad, maxEdad)
                    .stream()
                    .filter(PersonaEntity::getActivo)
                    .map(this::toResponse)
                    .toList();
            log.info("Total de personas encontradas en rango de edad: {}", personas.size());
            return personas;
        } catch (DataAccessException e) {
            log.error("Error de base de datos al buscar personas por rango de edad {}-{}: {}", minEdad, maxEdad, e.getMessage());
            return List.of();
        } catch (Exception e) {
            log.error("Error inesperado al buscar personas por rango de edad {}-{}: {}", minEdad, maxEdad, e.getMessage());
            return List.of();
        }
    }

    // Métodos con QUERY

    @Override
    public List<PersonaDtoResponse> buscarPorNombre(String nombre) {
        log.info("Buscando personas con nombre que contenga: {}", nombre);
        try {
            List<PersonaDtoResponse> personas = personaRepository.buscarPorNombre(nombre)
                    .stream()
                    .filter(PersonaEntity::getActivo)
                    .map(this::toResponse)
                    .toList();
            log.info("Total de personas encontradas con nombre '{}': {}", nombre, personas.size());
            return personas;

        } catch (DataAccessException e) {
            log.error("Error de base de datos al buscar personas por nombre '{}': {}", nombre, e.getMessage());
            return List.of();
        } catch (Exception e) {
            log.error("Error inesperado al buscar personas por nombre '{}': {}", nombre, e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<PersonaDtoResponse> obtenerPersonasPorEstado(Boolean activo) {
        log.info("Buscando personas con estado activo: {}", activo);
        try {
            List<PersonaDtoResponse> personas = personaRepository.obtenerPersonasPorEstado(activo)
                    .stream()
                    .map(this::toResponse)
                    .toList();
            log.info("Total de personas encontradas con estado {}: {}", activo, personas.size());
            return personas;
        } catch (DataAccessException e) {
            log.error("Error de base de datos al buscar personas por estado '{}': {}", activo, e.getMessage());
            return List.of();
        } catch (Exception e) {
            log.error("Error inesperado al buscar personas por estado '{}': {}", activo, e.getMessage());
            return List.of();
        }
    }


}