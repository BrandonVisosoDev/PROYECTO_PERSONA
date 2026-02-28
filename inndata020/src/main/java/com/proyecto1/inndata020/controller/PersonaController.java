package com.persona.proyectoPersona.controller;

import com.persona.proyectoPersona.entity.PersonaEntity;
import com.persona.proyectoPersona.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
