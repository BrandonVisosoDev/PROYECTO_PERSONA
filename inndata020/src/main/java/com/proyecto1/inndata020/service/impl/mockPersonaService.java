package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.PersonaClient;
import com.proyecto1.inndata020.feign.mockPersona;
import com.proyecto1.inndata020.service.ImockPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class mockPersonaService implements ImockPersona {

    @Autowired
    PersonaClient personaClient;

    @Override
    public List<mockPersona> getAll() {
        return personaClient.getAll();
    }

    @Override
    public mockPersona getById(Integer id) {
        return personaClient.getById(id);
    }

    @Override
    public mockPersona create(mockPersona persona) {
        return personaClient.create(persona);
    }

    @Override
    public mockPersona update(Integer id, mockPersona persona) {
        return personaClient.update(id, persona);
    }

    @Override
    public void delete(Integer id) {
        personaClient.delete(id);
    }
}