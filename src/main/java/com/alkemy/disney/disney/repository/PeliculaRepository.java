package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long>, JpaSpecificationExecutor<PeliculaEntity> {
    ArrayList<PeliculaEntity> findByNombre(String nombre);

    List<PeliculaEntity> findAll(Specification<PeliculaEntity> spec);

}