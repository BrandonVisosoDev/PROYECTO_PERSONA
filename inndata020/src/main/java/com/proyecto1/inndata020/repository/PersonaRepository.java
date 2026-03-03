package com.proyecto1.inndata020.repository;

import com.proyecto1.inndata020.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    // Query

    @Query("SELECT p FROM PersonaEntity p WHERE p.idDepartamento = :idDepartamento")
    List<PersonaEntity> findByDepartamento(@Param("idDepartamento") Long idDepartamento);

    // JPA

    List<PersonaEntity> findByIdDepartamento(Long idDepartamento);

    // Query

    @Query("SELECT p FROM PersonaEntity p WHERE p.edad BETWEEN :minEdad AND :maxEdad")
    List<PersonaEntity> findByEdadBetweenCustom(@Param("minEdad") int minEdad, @Param("maxEdad") int maxEdad);

    // JPA

    List<PersonaEntity> findByEdadBetween(int minEdad, int maxEdad);

    // Falta aprender lo visto ayer en clase





}
