package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pelicula")
@Getter
@Setter
public class PeliculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imagen;
    private String nombre;
    private Date creation_date;

    @Min(1)
    @Max(5)
    private Integer rating;

    @ManyToMany
    private Set<PersonajesEntity> personaje = new HashSet<>();

}
