package com.proyecto1.inndata020.repository;

import com.proyecto1.inndata020.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
}
