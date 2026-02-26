package com.persona.proyectoPersona.controller;

import com.persona.proyectoPersona.entity.PersonaEntity;
import com.persona.proyectoPersona.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")

public class PersonaController {

    @Autowired
    private IPersonaService personaService;

}