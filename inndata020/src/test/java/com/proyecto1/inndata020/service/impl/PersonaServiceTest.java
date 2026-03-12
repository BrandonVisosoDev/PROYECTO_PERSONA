package com.proyecto1.inndata020.service.impl;
import com.proyecto1.inndata020.model.PersonaDtoRequest;
import com.proyecto1.inndata020.model.PersonaDtoResponse;
import com.proyecto1.inndata020.entity.DepartamentoEntity;
import com.proyecto1.inndata020.entity.PersonaEntity;
import com.proyecto1.inndata020.repository.DepartamentoRepository;
import com.proyecto1.inndata020.repository.PersonaRepository;
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
class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private PersonaService personaService;

    private PersonaEntity personaEntity;
    private DepartamentoEntity departamentoEntity;
    private PersonaDtoRequest personaDtoRequest;

    @BeforeEach
    void setUp() {
        departamentoEntity = new DepartamentoEntity();
        departamentoEntity.setId(1);
        departamentoEntity.setM2(50);
        departamentoEntity.setPrecio(1500.0);
        departamentoEntity.setActivo(true);

        personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1);
        personaEntity.setNombre("Laura García");
        personaEntity.setDireccion("Calle Primavera 25");
        personaEntity.setEdad(23);
        personaEntity.setActivo(true);
        personaEntity.setDepartamento(departamentoEntity);

        personaDtoRequest = new PersonaDtoRequest();
        personaDtoRequest.setNombre("Laura García");
        personaDtoRequest.setDireccion("Calle Primavera 25");
        personaDtoRequest.setEdad(23);
        personaDtoRequest.setActivo(true);
        personaDtoRequest.setIdDepartamento(1);
    }

    @Test
    void listarPersonas() {
        when(personaRepository.findAll()).thenReturn(List.of(personaEntity));

        List<PersonaDtoResponse> resultado = personaService.listarPersonas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Laura García", resultado.get(0).getNombre());
        verify(personaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId() {
        when(personaRepository.findById(1)).thenReturn(Optional.of(personaEntity));

        Optional<PersonaDtoResponse> resultado = personaService.buscarPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals("Laura García", resultado.get().getNombre());
        verify(personaRepository, times(1)).findById(1);
    }

    @Test
    void buscarPorId_NoExiste() {
        when(personaRepository.findById(99)).thenReturn(Optional.empty());

        Optional<PersonaDtoResponse> resultado = personaService.buscarPorId(99);

        assertFalse(resultado.isPresent());
        verify(personaRepository, times(1)).findById(99);
    }

    @Test
    void guardarPersona() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamentoEntity));
        when(personaRepository.save(any(PersonaEntity.class))).thenReturn(personaEntity);

        String resultado = personaService.guardarPersona(personaDtoRequest);

        assertEquals("Persona creada exitosamente", resultado);
        verify(personaRepository, times(1)).save(any(PersonaEntity.class));
    }

    @Test
    void guardarPersona_DepartamentoNoExiste() {
        when(departamentoRepository.findById(1)).thenReturn(Optional.empty());

        String resultado = personaService.guardarPersona(personaDtoRequest);

        assertEquals("Departamento no encontrado", resultado);
        verify(personaRepository, never()).save(any());
    }

    @Test
    void actualizarPersona() {
        when(personaRepository.findById(1)).thenReturn(Optional.of(personaEntity));
        when(departamentoRepository.findById(1)).thenReturn(Optional.of(departamentoEntity));
        when(personaRepository.save(any(PersonaEntity.class))).thenReturn(personaEntity);

        String resultado = personaService.actualizarPersona(1, personaDtoRequest);

        assertEquals("Persona actualizada exitosamente", resultado);
        verify(personaRepository, times(1)).save(any(PersonaEntity.class));
    }

    @Test
    void actualizarPersona_NoExiste() {
        when(personaRepository.findById(99)).thenReturn(Optional.empty());

        String resultado = personaService.actualizarPersona(99, personaDtoRequest);

        assertEquals("Persona no encontrada", resultado);
        verify(personaRepository, never()).save(any());
    }

    @Test
    void borrarLogico() {
        when(personaRepository.findById(1)).thenReturn(Optional.of(personaEntity));
        when(personaRepository.save(any(PersonaEntity.class))).thenReturn(personaEntity);

        String resultado = personaService.borrarLogico(1);

        assertEquals("Persona desactivada exitosamente", resultado);
        assertFalse(personaEntity.getActivo());
        verify(personaRepository, times(1)).save(any(PersonaEntity.class));
    }

    @Test
    void borrarLogico_NoExiste() {
        when(personaRepository.findById(99)).thenReturn(Optional.empty());

        String resultado = personaService.borrarLogico(99);

        assertEquals("Persona no encontrada", resultado);
        verify(personaRepository, never()).save(any());
    }

    @Test
    void obtenerPersonasPorDepartamento() {
        when(personaRepository.findByDepartamento_Id(1)).thenReturn(List.of(personaEntity));

        List<PersonaDtoResponse> resultado = personaService.obtenerPersonasPorDepartamento(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(personaRepository, times(1)).findByDepartamento_Id(1);
    }

    @Test
    void obtenerPersonasPorRangoEdad() {
        when(personaRepository.findByEdadBetween(20, 30)).thenReturn(List.of(personaEntity));

        List<PersonaDtoResponse> resultado = personaService.obtenerPersonasPorRangoEdad(20, 30);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(23, resultado.get(0).getEdad());
        verify(personaRepository, times(1)).findByEdadBetween(20, 30);
    }
}