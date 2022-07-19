package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajesEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {

    public PersonajesEntity personajeDTO2entity(PersonajeDTO personajeDTO) {
        PersonajesEntity personajesEntity = new PersonajesEntity();
        personajesEntity.setId(personajeDTO.getId());
        personajesEntity.setImagen(personajeDTO.getImagen());
        personajesEntity.setNombre(personajeDTO.getNombre());
        personajesEntity.setEdad(personajeDTO.getEdad());
        personajesEntity.setPeso(personajeDTO.getPeso());
        personajesEntity.setHistoria(personajeDTO.getHistoria());

        return personajesEntity;
    }

    public PersonajeDTO personajeEntity2DTO (PersonajesEntity personajesEntity) {
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(personajesEntity.getId());
        personajeDTO.setImagen(personajesEntity.getImagen());
        personajeDTO.setNombre(personajesEntity.getNombre());
        personajeDTO.setEdad(personajesEntity.getEdad());
        personajeDTO.setPeso(personajesEntity.getPeso());
        personajeDTO.setHistoria(personajesEntity.getHistoria());
        personajeDTO.setPeliculas(toSetPeliDto(personajesEntity.getPeliculas()));
        return personajeDTO;
    }



    public Set<PeliculaDTO> toSetPeliDto(Set<PeliculaEntity> peliculas) {
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
                peliculaDTO.setGenero(peliculaDTO.getGenero());
                peliculaDTOS.add(peliculaDTO);
            }
        }
        return peliculaDTOS;
    }


    public List<PersonajeDTO> toListPersonajeDto(List<PersonajesEntity> personajes) {
        List<PersonajeDTO> PersonajeDtos = new ArrayList<>();
        if (!personajes.isEmpty()) {
            for (PersonajesEntity personaje : personajes) {
                PersonajeDTO personajeDTO = new PersonajeDTO();
                personajeDTO.setId(personaje.getId());
                personajeDTO.setImagen(personaje.getImagen());
                personajeDTO.setNombre(personaje.getNombre());
                personajeDTO.setEdad(personaje.getEdad());
                personajeDTO.setPeso(personaje.getPeso());
                personajeDTO.setHistoria(personaje.getHistoria());
                personajeDTO.setPeliculas(toSetPeliDto(personaje.getPeliculas()));
                PersonajeDtos.add(personajeDTO);
            }
        }
        return PersonajeDtos;
    }
}
