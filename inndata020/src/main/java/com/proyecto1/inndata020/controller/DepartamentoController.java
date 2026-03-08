package com.proyecto1.inndata020.controller;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.model.DepartamentoDtoRequest;
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
    public String create(@RequestBody DepartamentoDtoRequest departamento){
        return departamentoService.create(departamento);
    }
    @PutMapping("/departamentos/{id}")
    public String update(@PathVariable Integer id, @RequestBody DepartamentoEntity departamento){
        return departamentoService.updateById(id,departamento);
    }
    @DeleteMapping("/departamentos")
    public String deleteById(@PathParam("id") Integer id){
        return departamentoService.deleteById(id);

    }
    @GetMapping("/m2Precio")
    public List<DepartamentoEntity> m2AndPrecio(@PathParam("m2") Integer m2, @PathParam("precio") Double precio){
        return departamentoService.m2AndPrecio(m2,precio);
    }
    @GetMapping("/m2PrecioQ")
    public List<DepartamentoEntity> m2AndPrecioQ(@PathParam("m2") Integer m2, @PathParam("precio")Double precio){
        return departamentoService.m2PrecioQ(m2, precio);
    }
}
