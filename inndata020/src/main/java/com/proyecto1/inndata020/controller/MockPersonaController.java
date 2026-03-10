package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.feign.MockPersona;
import com.proyecto1.inndata020.service.impl.MockPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MockPersonaController {

    private final MockPersonaService mockPersonaService;

    @Autowired
    public MockPersonaController(MockPersonaService mockPersonaService) {
        this.mockPersonaService = mockPersonaService; // nombre correcto en minúscula
    }

    @GetMapping("/persona")
    public List<MockPersona> getAll() {
        return mockPersonaService.getAll(); // usa la variable correcta
    }

    @GetMapping("/persona/{id}")
    public MockPersona getById(@PathVariable Integer id) {
        return mockPersonaService.getById(id);
    }

    @PostMapping("/persona")
    public MockPersona create(@RequestBody MockPersona persona) {
        return mockPersonaService.create(persona);
    }

    @PutMapping("/persona/{id}")
    public MockPersona update(@PathVariable Integer id, @RequestBody MockPersona persona) {
        return mockPersonaService.update(id, persona);
    }

    @DeleteMapping("/persona/{id}")
    public void delete(@PathVariable Integer id) {
        mockPersonaService.delete(id);
    }
}