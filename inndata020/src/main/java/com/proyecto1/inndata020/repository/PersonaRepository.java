package com.proyecto1.inndata020.repository;

import com.proyecto1.inndata020.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    // JPA Derivado
    List<PersonaEntity> findByDepartamento_Id(Integer idDepartamento); // ✅ navega la relación

    // Query
    @Query("SELECT p FROM PersonaEntity p WHERE p.departamento.id = :idDepartamento")
    List<PersonaEntity> findByDepartamento(@Param("idDepartamento") Integer idDepartamento); // ✅

    // JPA Derivado
    List<PersonaEntity> findByEdadBetween(Integer minEdad, Integer maxEdad); // ✅ int → Integer

    // Query
    @Query("SELECT p FROM PersonaEntity p WHERE p.edad BETWEEN :minEdad AND :maxEdad")
    List<PersonaEntity> findByEdadBetweenCustom(@Param("minEdad") Integer minEdad, @Param("maxEdad") Integer maxEdad); // ✅
}