package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.feign.mockPersona;
import com.proyecto1.inndata020.service.impl.mockPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MockPersonaController {

    private final mockPersonaService mockPersonaService;


    @Autowired
    public MockPersonaController(mockPersonaService mockPersonaService) {
        this.mockPersonaService = mockPersonaService;
    }

    @GetMapping("/persona")
    public List<mockPersona> getAll() {
        return mockPersonaService.getAll();
    }

    @GetMapping("/persona/{id}")
    public mockPersona getById(@PathVariable Integer id) {
        return mockPersonaService.getById(id);
    }

    @PostMapping("/persona")
    public mockPersona create(@RequestBody mockPersona persona) {
        return mockPersonaService.create(persona);
    }

    @PutMapping("/persona/{id}")
    public mockPersona update(@PathVariable Integer id, @RequestBody mockPersona persona) {
        return mockPersonaService.update(id, persona);
    }

    @DeleteMapping("/persona/{id}")
    public void delete(@PathVariable Integer id) {
        mockPersonaService.delete(id);
    }
}