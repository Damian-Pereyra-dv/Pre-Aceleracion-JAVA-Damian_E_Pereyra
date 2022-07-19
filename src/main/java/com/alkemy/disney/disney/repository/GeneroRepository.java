package com.alkemy.disney.disney.repository;


import com.alkemy.disney.disney.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GeneroRepository extends JpaRepository  <GeneroEntity, Long>{

    ArrayList<GeneroEntity> findByName(String name);


}
