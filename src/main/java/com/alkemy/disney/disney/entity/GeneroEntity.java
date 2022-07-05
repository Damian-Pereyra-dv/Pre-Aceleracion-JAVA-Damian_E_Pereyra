package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="genero")
@Getter
@Setter
public class GeneroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imagen;
    private String nombre;

    @ManyToMany
    private Set<PeliculaEntity> peliculas = new HashSet<>();

}
