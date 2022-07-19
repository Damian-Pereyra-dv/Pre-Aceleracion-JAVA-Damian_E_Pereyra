package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajesEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PeliculaMapper {



    public PeliculaDTO tpPeliculaEntity2DTO(PeliculaEntity pelicula) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        if(pelicula != null) {
            peliculaDTO.setId(pelicula.getId());
            peliculaDTO.setImagen(pelicula.getImagen());
            peliculaDTO.setNombre(pelicula.getNombre());
            peliculaDTO.setRating(pelicula.getRating());
            peliculaDTO.setFechaCreacion(pelicula.getFechaCreacion());
            peliculaDTO.setPersonajes(toSetPersonajeDto(pelicula.getPersonajes()));
            peliculaDTO.setGenero((GeneroEntity) toSetGeneroDto(pelicula.getGenero()));
        }
        return peliculaDTO;
    }

    private Set<PersonajeDTO> toSetPersonajeDto(Set<PersonajesEntity> personajes) {
        Set<PersonajeDTO> personajeDTOS = new HashSet<>();
        for (PersonajesEntity personaje : personajes) {
            PersonajeDTO personajeDTO = new PersonajeDTO();
            personajeDTO.setId(personaje.getId());
            personajeDTO.setImagen(personaje.getImagen());
            personajeDTO.setNombre(personaje.getNombre());
            personajeDTO.setEdad(personaje.getEdad());
            personajeDTO.setPeso(personaje.getPeso());
            personajeDTO.setHistoria(personaje.getHistoria());
            personajeDTOS.add(personajeDTO);
        }
        return personajeDTOS;
    }

    private Set<GeneroDTO> toSetGeneroDto(Set<GeneroEntity> generos) {
        Set<GeneroDTO> generoDtos = new HashSet<>();
        if (generos != null) {
            for (GeneroEntity genero : generos) {
                GeneroDTO generoDTO = new GeneroDTO();
                generoDTO.setId(genero.getId());
                generoDTO.setImagen(genero.getImagen());
                generoDTO.setNombre(genero.getNombre());
                generoDtos.add(generoDTO);
            }
        }
        return generoDtos;
    }

    public PeliculaEntity toPeliculaDTO2entity(PeliculaDTO peliculaDTO) {
        PeliculaEntity pelicula = new PeliculaEntity();
        pelicula.setId(peliculaDTO.getId());
        pelicula.setImagen(peliculaDTO.getImagen());
        pelicula.setNombre(peliculaDTO.getNombre());
        pelicula.setRating(peliculaDTO.getRating());
        pelicula.setFechaCreacion(peliculaDTO.getFechaCreacion());
        pelicula.setPersonajes(toSetPersonajesEntity(peliculaDTO.getPersonajes()));
        pelicula.setGenero(toSetGeneroEntity((Set<GeneroDTO>) peliculaDTO.getGenero()));
        return pelicula;
    }

    private Set<PersonajesEntity> toSetPersonajesEntity(Set<PersonajeDTO> personajes) {
        Set<PersonajesEntity> personajesSet = new HashSet<>();
        for (PersonajeDTO personajeDTO : personajes) {
            PersonajesEntity personaje = new PersonajesEntity();
            personaje.setId(personajeDTO.getId());
            personaje.setImagen(personajeDTO.getImagen());
            personaje.setNombre(personajeDTO.getNombre());
            personaje.setEdad(personajeDTO.getEdad());
            personaje.setPeso(personajeDTO.getPeso());
            personaje.setHistoria(personajeDTO.getHistoria());
            personajesSet.add(personaje);
        }
        return personajesSet;
    }

    private Set<GeneroEntity> toSetGeneroEntity(Set<GeneroDTO> generos) {
        Set<GeneroEntity> generoSet =new HashSet<>();
        for(GeneroDTO generoDTO : generos) {
            GeneroEntity genero = new GeneroEntity();
            genero.setId(generoDTO.getId());
            genero.setNombre(generoDTO.getNombre());
            genero.setImagen(generoDTO.getImagen());
            generoSet.add(genero);
        }
        return generoSet;
    }

    public List<PeliculaDTO> toListPeliculaDTO(List<PeliculaEntity> peliculas) {
        List<PeliculaDTO> movieDtos = new ArrayList<>();
        if (!peliculas.isEmpty()) {
            for (PeliculaEntity pelicula : peliculas) {
                PeliculaDTO peliculaDTO = new PeliculaDTO();
                peliculaDTO.setId(pelicula.getId());
                peliculaDTO.setImagen(pelicula.getImagen());
                peliculaDTO.setNombre(pelicula.getNombre());
                peliculaDTO.setFechaCreacion(pelicula.getFechaCreacion());
                peliculaDTO.setRating(pelicula.getRating());
                peliculaDTO.setPersonajes(toSetPersonajeDto(pelicula.getPersonajes()));
                peliculaDTO.setGenero((GeneroEntity) toSetGeneroDto(pelicula.getGenero()));
                movieDtos.add(peliculaDTO);
            }
        }
        return movieDtos;
    }
}
