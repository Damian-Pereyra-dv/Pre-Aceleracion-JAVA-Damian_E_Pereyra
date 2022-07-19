package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PersonajesEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Date fechaCreacion;
    private Integer rating;
    private GeneroEntity genero;
    private Set<PersonajeDTO> personajes;
}
