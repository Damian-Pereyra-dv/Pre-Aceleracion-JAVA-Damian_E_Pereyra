package com.alkemy.disney.disney.repository;


import com.alkemy.disney.disney.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository  <GeneroEntity, Long>{


}
