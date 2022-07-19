package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

public class PersonajeDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Float peso;
    private String historia;
    private Set<PeliculaDTO> peliculas ;


}
