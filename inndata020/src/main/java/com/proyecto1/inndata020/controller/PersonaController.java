package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.model.PersonaDtoRequest;
import com.proyecto1.inndata020.model.PersonaDtoResponse;
import com.proyecto1.inndata020.service.IPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class PersonaController {

    private final IPersonaService personaService;

    @Autowired
    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/personas")
    public List<PersonaDtoResponse> listarPersonas() {
        log.info("Solicitud para listar todas las personas");
        return personaService.listarPersonas();
    }

    @GetMapping("/personas/{id}")
    public Optional<PersonaDtoResponse> buscarPorId(@PathVariable Integer id) {
        log.info("Solicitud para buscar persona con id: {}", id);
        return personaService.buscarPorId(id);
    }

    @PostMapping("/personasguardar")
    public String guardarPersona(@RequestBody PersonaDtoRequest persona) {
        log.info("Solicitud para guardar persona: {}", persona.getNombre());
        return personaService.guardarPersona(persona);
    }

    @PutMapping("/personasactualizar/{id}")
    public String actualizarPersona(@PathVariable Integer id, @RequestBody PersonaDtoRequest persona) {
        log.info("Solicitud para actualizar persona con id: {}", id);
        return personaService.actualizarPersona(id, persona);
    }

    @DeleteMapping("/personaseliminar/{id}")
    public String borrarLogico(@PathVariable Integer id) {
        log.info("Solicitud para desactivar persona con id: {}", id);
        return personaService.borrarLogico(id);
    }

    // JPA PERSONALIZADOS

    @GetMapping("/personas/departamento/{idDepartamento}")
    public List<PersonaDtoResponse> obtenerPorDepartamento(@PathVariable Integer idDepartamento) {
        log.info("Solicitud para listar personas del departamento: {}", idDepartamento);
        return personaService.obtenerPersonasPorDepartamento(idDepartamento);
    }

    @GetMapping("/personas/edad")
    public List<PersonaDtoResponse> obtenerPorRangoEdad(
            @RequestParam Integer minEdad,
            @RequestParam Integer maxEdad) {
        log.info("Solicitud para listar personas con edad entre {} y {}", minEdad, maxEdad);
        return personaService.obtenerPersonasPorRangoEdad(minEdad, maxEdad);
    }

    // Metodos con QUERY

    // QUERY

    @GetMapping("/personas/nombre")
    public List<PersonaDtoResponse> buscarPorNombre(@RequestParam String nombre) {
        log.info("Solicitud para buscar personas con nombre que contenga: {}", nombre);
        return personaService.buscarPorNombre(nombre);
    }

    @GetMapping("/personas/estado")
    public List<PersonaDtoResponse> obtenerPorEstado(@RequestParam Boolean activo) {
        log.info("Solicitud para listar personas con estado activo: {}", activo);
        return personaService.obtenerPersonasPorEstado(activo);
    }


}