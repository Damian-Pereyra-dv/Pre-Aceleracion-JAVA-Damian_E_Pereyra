package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GeneroDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Set<PeliculaDTO> peliculas;

}
