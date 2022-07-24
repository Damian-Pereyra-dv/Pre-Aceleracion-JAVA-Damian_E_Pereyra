package com.alkemy.disney.disney.repository;



import com.alkemy.disney.disney.entity.GenreEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
