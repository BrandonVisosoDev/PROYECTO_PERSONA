package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.PersonaClient;
import com.proyecto1.inndata020.feign.MockPersona;
import com.proyecto1.inndata020.service.ImockPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockPersonaService implements ImockPersona {

    private final PersonaClient personaClient;

    @Autowired
    public MockPersonaService(PersonaClient personaClient) {
        this.personaClient = personaClient;
    }

    @Override
    public List<MockPersona> getAll() {
        return personaClient.getAll();
    }

    @Override
    public MockPersona getById(Integer id) {
        return personaClient.getById(id);
    }

    @Override
    public MockPersona create(MockPersona persona) {
        return personaClient.create(persona);
    }

    @Override
    public MockPersona update(Integer id, MockPersona persona) {
        return personaClient.update(id, persona);
    }

    @Override
    public void delete(Integer id) {
        personaClient.delete(id);
    }
}