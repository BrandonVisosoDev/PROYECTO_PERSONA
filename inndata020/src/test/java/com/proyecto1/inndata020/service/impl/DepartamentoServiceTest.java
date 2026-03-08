package com.proyecto1.inndata020.service.impl;

import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.model.DepartamentoDtoRequest;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoService departamentoService;

    private DepartamentoEntity departamentoEntity;
    private DepartamentoDtoRequest departamentoDtoRequest;

    @BeforeEach
    void setUp() {
        departamentoEntity = new DepartamentoEntity();
        departamentoEntity.setId(1);
        departamentoEntity.setM2(50);
        departamentoEntity.setPrecio(1500.0);
        departamentoEntity.setActivo(true);

        departamentoDtoRequest = new DepartamentoDtoRequest();
        departamentoDtoRequest.setM2(50);
        departamentoDtoRequest.setPrecio(1500.0);
        departamentoDtoRequest.setActivo(true);
    }

    @Test
    void readAll() {
        DepartamentoEntity inactivo = new DepartamentoEntity();
        inactivo.setId(2);
        inactivo.setActivo(false);

        when(departamentoRepository.findAll()).thenReturn(List.of(departamentoEntity, inactivo));

        List<DepartamentoEntity> resultado = departamentoService.readAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size()); // ✅ solo regresa los activos
        assertTrue(resultado.get(0).getActivo());
        verify(departamentoRepository, times(1)).findAll();
    }

    @Test
    void readById() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamentoEntity));

        Optional<DepartamentoEntity> resultado = departamentoService.readById(1);

        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getId());
        verify(departamentoRepository, times(1)).findById(1);
    }

    @Test
    void readById_NoExiste() {
        when(departamentoRepository.findById(99)).thenReturn(Optional.empty());

        Optional<DepartamentoEntity> resultado = departamentoService.readById(99);

        assertFalse(resultado.isPresent());
        verify(departamentoRepository, times(1)).findById(99);
    }

    @Test
    void create() {
        when(departamentoRepository.save(any(DepartamentoEntity.class))).thenReturn(departamentoEntity);

        String resultado = departamentoService.create(departamentoDtoRequest);

        assertEquals("Departamento creado de manera exitosa", resultado);
        verify(departamentoRepository, times(1)).save(any(DepartamentoEntity.class));
    }

    @Test
    void updateById() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamentoEntity));
        when(departamentoRepository.save(any(DepartamentoEntity.class))).thenReturn(departamentoEntity);

        String resultado = departamentoService.updateById(1, departamentoDtoRequest);

        assertEquals("Departamento actualizado", resultado);
        verify(departamentoRepository, times(1)).save(any(DepartamentoEntity.class));
    }

    @Test
    void updateById_NoExiste() {
        when(departamentoRepository.findById(99)).thenReturn(Optional.empty());

        String resultado = departamentoService.updateById(99, departamentoDtoRequest);

        assertEquals("Departamento no encontrado", resultado);
        verify(departamentoRepository, never()).save(any());
    }

    @Test
    void deleteById() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamentoEntity));
        when(departamentoRepository.save(any(DepartamentoEntity.class))).thenReturn(departamentoEntity);

        String resultado = departamentoService.deleteById(1);

        assertEquals("Departamento borrado", resultado);
        assertFalse(departamentoEntity.getActivo()); // ✅ verifica borrado lógico
        verify(departamentoRepository, times(1)).save(any(DepartamentoEntity.class));
    }

    @Test
    void deleteById_NoExiste() {
        when(departamentoRepository.findById(99)).thenReturn(Optional.empty());

        String resultado = departamentoService.deleteById(99);

        assertEquals("No existe tal departamento", resultado);
        verify(departamentoRepository, never()).save(any());
    }

    @Test
    void m2AndPrecio() {
        when(departamentoRepository.findByM2LessThanAndPrecioIs(60, 1500.0))
                .thenReturn(List.of(departamentoEntity));

        List<DepartamentoEntity> resultado = departamentoService.m2AndPrecio(60, 1500.0);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(departamentoRepository, times(1)).findByM2LessThanAndPrecioIs(60, 1500.0);
    }

    @Test
    void m2PrecioQ() {
        when(departamentoRepository.menorQueM2Precio(60, 1500.0))
                .thenReturn(List.of(departamentoEntity));

        List<DepartamentoEntity> resultado = departamentoService.m2PrecioQ(60, 1500.0);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(departamentoRepository, times(1)).menorQueM2Precio(60, 1500.0);
    }
}