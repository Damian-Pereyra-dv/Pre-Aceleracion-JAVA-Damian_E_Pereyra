package com.alkemy.disney.disney.validador;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidadorDTO {

    public boolean peliculaDTOvalida(PeliculaDTO peliculaDTO) {
        boolean valid = false;
        if (peliculaDTO.getImagen() != null &&
                peliculaDTO.getNombre() != null &&
                peliculaDTO.getFechaCreacion() != null &&
                peliculaDTO.getRating() >= 1 &&
                peliculaDTO.getRating() <= 5 &&
                peliculaDTO.getPersonajes() != null &&
                peliculaDTO.getGenero() != null) {
            if (!peliculaDTO.getPersonajes().isEmpty()) {
                valid = iterarPersonajes(peliculaDTO.getPersonajes());
            }
            if (!peliculaDTO.getPersonajes().isEmpty()) {
                valid = iterarGeneros((Set<GeneroDTO>) peliculaDTO.getGenero()); // problema de casteo ya que no es lista, es un objeto GeneroEntity, revisar
            }
        }
        return valid;
    }

    private boolean iterarPersonajes(Set<PersonajeDTO> personajesDTO) {
        for (PersonajeDTO personajeDTO : personajesDTO) {
            if (!persojaneDTOvalido(personajeDTO)) {
                return false;
            }
        }
        return true;
    }

    public boolean persojaneDTOvalido(PersonajeDTO personajeDTO) {
        return personajeDTO.getImagen() != null &&
                personajeDTO.getNombre() != null &&
                personajeDTO.getHistoria() != null &&
                personajeDTO.getEdad() > 0 &&
                personajeDTO.getPeso() > 0;
    }

    private boolean iterarGeneros(Set<GeneroDTO> generos) {
        for (GeneroDTO generoDTO : generos) {
            if (!generoDTOvalido(generoDTO)) {
                return false;
            }
        }
        return true;
    }

    public boolean generoDTOvalido(GeneroDTO generoDto) {
        return generoDto.getImagen() != null &&
                generoDto.getNombre() != null;
    }

    public boolean peliculaDTOupdateValida(PeliculaDTO peliculaDTO) {
        return peliculaDTO.getImagen() != null &&
                peliculaDTO.getNombre() != null &&
                peliculaDTO.getFechaCreacion() != null &&
                peliculaDTO.getRating() >= 1 &&
                peliculaDTO.getRating() <= 5;
    }

}
