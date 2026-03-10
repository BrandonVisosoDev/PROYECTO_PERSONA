package com.proyecto1.inndata020.service;


import com.proyecto1.inndata020.feign.MockPersona;


import java.util.List;

public interface ImockPersona {

    List<MockPersona> getAll();
    MockPersona getById(Integer id);
    MockPersona create(MockPersona persona);
    MockPersona update(Integer id, MockPersona persona);
    void delete(Integer id);


}
