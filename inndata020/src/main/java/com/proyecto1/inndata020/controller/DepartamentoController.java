package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.model.DepartamentoDtoRequest;
import com.proyecto1.inndata020.service.impl.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DepartamentoController {

    // Inyección por constructor

    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }


    @GetMapping("/departamentos")
    public List<DepartamentoEntity> readAll() {
        return departamentoService.readAll();
    }

    @GetMapping("/departamentos/{id}")
    public Optional<DepartamentoEntity> readById(@PathVariable Integer id) {
        return departamentoService.readById(id);
    }

    @PostMapping("/departamentos")
    public String create(@RequestBody DepartamentoDtoRequest departamento) {
        return departamentoService.create(departamento);
    }

    @PutMapping("/departamentos/{id}")
    public String update(@PathVariable Integer id, @RequestBody DepartamentoDtoRequest departamento) { // ⚠️ Entity → DTO
        return departamentoService.updateById(id, departamento);
    }

    @DeleteMapping("/departamentos/{id}") // ⚠️ id en el path
    public String deleteById(@PathVariable Integer id) { // ⚠️ @PathParam → @PathVariable
        return departamentoService.deleteById(id);
    }

    @GetMapping("/m2Precio")
    public List<DepartamentoEntity> m2AndPrecio(@RequestParam Integer m2, @RequestParam Double precio) {
        return departamentoService.m2AndPrecio(m2, precio);
    }

    @GetMapping("/m2PrecioQ")
    public List<DepartamentoEntity> m2AndPrecioQ(@RequestParam Integer m2, @RequestParam Double precio) {
        return departamentoService.m2PrecioQ(m2, precio);
    }
}