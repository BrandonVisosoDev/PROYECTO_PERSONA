package com.proyecto1.inndata020.entity;

import com.proyecto1.inndata020.feign.MockPersona;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "personaClient", url = "https://69ac8d709ca639a5217f29e2.mockapi.io")
public interface PersonaClient {

    @GetMapping("/persona")
    List<MockPersona> getAll();

    @GetMapping("/persona/{id}")
    MockPersona getById(@PathVariable("id") Integer id);

    @PostMapping("/persona")
    MockPersona create(@RequestBody MockPersona persona);

    @PutMapping("/persona/{id}")
    MockPersona update(@PathVariable("id") Integer id, @RequestBody MockPersona persona);

    @DeleteMapping("/persona/{id}")
    void delete(@PathVariable("id") Integer id);
}