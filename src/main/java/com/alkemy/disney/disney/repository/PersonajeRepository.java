package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.PersonajesEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajesEntity, Long> {

 ArrayList<PersonajesEntity> findByName(String nombre);

    List<PersonajesEntity> findAll(Specification<PersonajesEntity> spec);


    }


