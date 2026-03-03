package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.entity.PersonaEntity;
import com.proyecto1.inndata020.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")

public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    // SELECCIONA A TODAS LAS PERSONAS

    @GetMapping("/listar")
    public List<PersonaEntity> listarPersonas() {
        return personaService.listarPersonas();
    }

    // BUSCA POR ID

    @GetMapping("/buscar/{id}")
    public PersonaEntity buscarPorId(@PathVariable Integer id) {
        return personaService.buscarPorId(id);
    }

    // GUARDA UNA NUEVA PERSONA

    @PostMapping("/guardar")
    public PersonaEntity guardarPersona(@RequestBody PersonaEntity persona) {
        return personaService.guardarPersona(persona);
    }

    // ACTUALIZA UNA PERSONA EXISTENTE

    @PutMapping("/actualizar/{id}")
    public PersonaEntity actualizarPersona(@PathVariable Integer id, @RequestBody PersonaEntity persona) {
        return personaService.actualizarPersona(id, persona);
    }

    // Borrado lógico
    @DeleteMapping("/{id}")
    public PersonaEntity borrarLogico(@PathVariable Integer id) {
        return personaService.borrarLogico(id);
    }



    // Obtener personas por departamento
    @GetMapping("/departamento/{idDepartamento}")
    public List<PersonaEntity> obtenerPorDepartamento(@PathVariable Long idDepartamento) {
        return personaService.obtenerPersonasPorDepartamento(idDepartamento); // JPA Derivado
        // return personaService.obtenerPersonasPorDepartamento(idDepartamento); // @Query (misma llamada, cambia solo en el Service/Repository)
    }

    // Filtrar por edad y obtener
    @GetMapping("/edad")
    public List<PersonaEntity> obtenerPorRangoEdad(
            @RequestParam int minEdad,
            @RequestParam int maxEdad) {
        return personaService.obtenerPersonasPorRangoEdad(minEdad, maxEdad); // JPA Derivado
        // return personaService.obtenerPersonasPorRangoEdad(minEdad, maxEdad); // @Query (misma llamada, cambia solo en el Service/Repository)
    }







}

