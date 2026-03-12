package com.proyecto1.inndata020.repository;

import com.proyecto1.inndata020.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    // JPA Derivado - se quedan igual ✅
    List<PersonaEntity> findByDepartamento_Id(Integer idDepartamento);

    List<PersonaEntity> findByEdadBetween(Integer minEdad, Integer maxEdad);

    // Query - ahora con propósito distinto ✅
    @Query("SELECT p FROM PersonaEntity p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<PersonaEntity> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM PersonaEntity p WHERE p.activo = :activo")
    List<PersonaEntity> obtenerPersonasPorEstado(@Param("activo") Boolean activo);


}