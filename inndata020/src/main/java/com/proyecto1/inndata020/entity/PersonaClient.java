package com.proyecto1.inndata020.entity;

import com.proyecto1.inndata020.feign.mockPersona;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "id_persona", url = "https://69ac8d709ca639a5217f29e2.mockapi.io")
public interface PersonaClient {

    @GetMapping("/persona")
    List<mockPersona> getAll();

    @GetMapping("/persona/{id}")
    mockPersona getById(@PathVariable("id") Integer id);

    @PostMapping("/persona")
    mockPersona create(@RequestBody mockPersona persona);

    @PutMapping("/persona/{id}")
    mockPersona update(@PathVariable("id") Integer id, @RequestBody mockPersona persona);

    @DeleteMapping("/persona/{id}")
    void delete(@PathVariable("id") Integer id);
}