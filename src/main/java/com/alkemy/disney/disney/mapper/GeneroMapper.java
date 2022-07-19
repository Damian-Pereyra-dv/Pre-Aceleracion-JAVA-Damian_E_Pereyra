package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GeneroMapper {


    public GeneroEntity toGeneroDTO2Entity(GeneroDTO generoDTO) {
        GeneroEntity genero = new GeneroEntity();
        genero.setId(generoDTO.getId());
        genero.setNombre(generoDTO.getNombre());
        genero.setImagen(generoDTO.getImagen());
        return genero;
    }

    public GeneroDTO toGeneroEntity2DTO(GeneroEntity genero) {
        GeneroDTO generoDTO = new GeneroDTO();
        if (genero != null) {
            generoDTO.setId(genero.getId());
            generoDTO.setImagen(genero.getImagen());
            generoDTO.setNombre(genero.getNombre());
            generoDTO.setPeliculas(toSetPeliculaDto(genero.getPeliculas()));
        }
        return generoDTO;
    }


    public Set<PeliculaDTO> toSetPeliculaDto(Set<PeliculaEntity> peliculas) {
        Set<PeliculaDTO> peliculaDTOS = new HashSet<>();
        if (peliculas != null) {
            for (PeliculaEntity pelicula : peliculas) {
                PeliculaDTO peliculaDTO = new PeliculaDTO();
                peliculaDTO.setId(pelicula.getId());
                peliculaDTO.setImagen(pelicula.getImagen());
                peliculaDTO.setNombre(pelicula.getNombre());
                peliculaDTO.setRating(pelicula.getRating());
                peliculaDTO.setFechaCreacion(pelicula.getFechaCreacion());
                peliculaDTO.setPersonajes(new HashSet<>());
                peliculaDTO.setGenero((GeneroEntity) pelicula.getGenero());
                peliculaDTOS.add(peliculaDTO);
            }
        }
        return peliculaDTOS;
    }

    public List<GeneroDTO> toListGeneroDto(List<GeneroEntity> generos) {
        List<GeneroDTO> generoDTOS = new ArrayList<>();
        if (generos != null) {
            for (GeneroEntity genero : generos) {
                GeneroDTO generoDTO = new GeneroDTO();
                generoDTO.setId(genero.getId());
                generoDTO.setImagen(genero.getImagen());
                generoDTO.setNombre(genero.getNombre());
                generoDTOS.add(generoDTO);
            }
        }
        return generoDTOS;


    }

}


