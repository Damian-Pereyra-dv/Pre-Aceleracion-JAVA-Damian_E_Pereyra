package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="personaje")
@Getter
@Setter
public class PersonajesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;

    @ManyToMany(mappedBy = "personaje")
    private Set<PeliculaEntity> peliculas = new HashSet<>();


}
