package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.model.PersonaDtoRequest;
import com.proyecto1.inndata020.model.PersonaDtoResponse;
import com.proyecto1.inndata020.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/personas")
    public List<PersonaDtoResponse> listarPersonas() {
        return personaService.listarPersonas();
    }

    @GetMapping("/personas/{id}")
    public Optional<PersonaDtoResponse> buscarPorId(@PathVariable Integer id) {
        return personaService.buscarPorId(id);
    }

    @PostMapping("/personasguardar")
    public String guardarPersona(@RequestBody PersonaDtoRequest persona) {
        return personaService.guardarPersona(persona);
    }

    @PutMapping("/personasactualizar/{id}")
    public String actualizarPersona(@PathVariable Integer id, @RequestBody PersonaDtoRequest persona) {
        return personaService.actualizarPersona(id, persona);
    }

    @DeleteMapping("/personaseliminar/{id}")
    public String borrarLogico(@PathVariable Integer id) {
        return personaService.borrarLogico(id);
    }

    @GetMapping("/personas/departamento/{idDepartamento}")
    public List<PersonaDtoResponse> obtenerPorDepartamento(@PathVariable Integer idDepartamento) { // ⚠️ Long → Integer
        return personaService.obtenerPersonasPorDepartamento(idDepartamento);
    }

    @GetMapping("/personas/edad")
    public List<PersonaDtoResponse> obtenerPorRangoEdad(
            @RequestParam Integer minEdad,
            @RequestParam Integer maxEdad) { // ⚠️ int → Integer
        return personaService.obtenerPersonasPorRangoEdad(minEdad, maxEdad);
    }
}