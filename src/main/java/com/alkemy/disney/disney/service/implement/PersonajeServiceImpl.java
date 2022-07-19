package com.alkemy.disney.disney.service.implement;


import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajesEntity;
import com.alkemy.disney.disney.exception.msj.ErrorMensajes;
import com.alkemy.disney.disney.exception.personajeException;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.especificaciones.PersonajeEspecifico;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.validador.ValidadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service

public class PersonajeServiceImpl {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeMapper personajeMapper;

   @Autowired
   private ValidadorDTO validadorDTO;

   @Autowired
   private PersonajeEspecifico personajeEspecifico;

    @Autowired
    private PeliculaServiceImpl peliculaService;

    public List<PersonajeDTO> getAllCharacters() {
        List<PersonajesEntity> personajes = personajeRepository.findAll();
        return personajeMapper.toListPersonajeDto(personajes);
    }

    public PersonajeDTO savePersonaje(PersonajeDTO personajeDTO) {
        if (validadorDTO.persojaneDTOvalido(personajeDTO)) {
            PersonajesEntity personaje = personajeMapper.personajeDTO2entity(personajeDTO);
            personaje = personajeRepository.save(personaje);
            return personajeMapper.personajeEntity2DTO(personaje);
        } else {
            throw new personajeException(ErrorMensajes.PERSONAJE_NO_ENCONTRADO);
        }
    }

    public PersonajeDTO updatePersonaje(PersonajeDTO personajeDTO) {
        if (personajeRepository.existsById(personajeDTO.getId())){
            if (validadorDTO.persojaneDTOvalido(personajeDTO)) {
                PersonajesEntity personaje = personajeMapper.personajeDTO2entity(personajeDTO);
                personaje = personajeRepository.save(personaje);
                return personajeMapper.personajeEntity2DTO(personaje);
            } else {
                throw new personajeException(ErrorMensajes.DTO_INCORRECTO_DATA);
            }
        } else {
            throw new personajeException(ErrorMensajes.PERSONAJE_NO_ENCONTRADO);
        }
    }

    public void deletePersonaje(Long id) {
        if (personajeRepository.existsById(id)) {
            unCharacter(id);
            personajeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(ErrorMensajes.PERSONAJE_NO_ENCONTRADO);
        }
    }

    private void unCharacter(Long id){
        PersonajesEntity personaje = personajeRepository.getReferenceById(id);
        if (!personaje.getPeliculas().isEmpty()) {
            Set<PeliculaEntity> peliculas = personaje.getPeliculas();
            for (PeliculaEntity pelicula : peliculas) {
                peliculaService.removerPersonajeToPelicula(personaje.getId(), pelicula.getId());
            }
        }
    }

    public PersonajeDTO getPersonaje(Long id) {
        if (personajeRepository.existsById(id)) {
            PersonajesEntity personaje = personajeRepository.getReferenceById(id);
            return personajeMapper.personajeEntity2DTO(personaje);
        } else {
            throw new EntityNotFoundException(ErrorMensajes.PERSONAJE_NO_ENCONTRADO);
        }
    }

    public List<PersonajeDTO> getPersonajeFiltrado(String nombre, Integer edad, Set<Long> peliculas) {
        List<PersonajesEntity>characters = personajeRepository.findAll(
                personajeEspecifico.getByFilters(nombre, edad, peliculas));
        return personajeMapper.toListPersonajeDto(characters);
    }
}
