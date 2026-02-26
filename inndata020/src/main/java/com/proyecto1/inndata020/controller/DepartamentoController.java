package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.service.impl.DepartamentoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DepartamentoController {
    @Autowired
    DepartamentoService departamentoService;
    /*GET (leer), POST (crear),
     PUT (actualizar total), PATCH (actualizar parcial) y DELETE (eliminar).
     */
    @GetMapping("/departamentos")
    public List<DepartamentoEntity> readAll(){
        return departamentoService.readAll();
    }

    @GetMapping("/departamentos/{id}")
    public Optional<DepartamentoEntity> readById(@PathVariable Integer id){
        return departamentoService.readById(id);
    }

    @PostMapping("/departamentos")
    public String create(@RequestBody DepartamentoEntity departamento){
        return departamentoService.create(departamento);
    }
    @PutMapping("/departamentos/{id}")
    public String update(@PathVariable Integer id, @RequestBody DepartamentoEntity departamento){
        return departamentoService.updateById(id,departamento);
    }
}
