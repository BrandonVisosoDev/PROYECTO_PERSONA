package com.proyecto1.inndata020.service;


import com.proyecto1.inndata020.feign.mockPersona;

import java.util.List;

public interface ImockPersona {

    List<mockPersona> getAll();
    mockPersona getById(Integer id);
    mockPersona create(mockPersona persona);
    mockPersona update(Integer id, mockPersona persona);
    void delete(Integer id);


}
