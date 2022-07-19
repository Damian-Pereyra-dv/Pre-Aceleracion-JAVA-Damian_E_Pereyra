package com.alkemy.disney.disney.service.implement;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajesEntity;
import com.alkemy.disney.disney.exception.PeliculaException;
import com.alkemy.disney.disney.exception.msj.ErrorMensajes;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.especificaciones.PeliculaEspecificacion;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.validador.ValidadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PeliculaServiceImpl {
    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private ValidadorDTO validadorDTO;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PeliculaEspecificacion peliculaEspecificacion;

    // Get all
    public List<PeliculaDTO> getAllPeliculas() {
        List<PeliculaEntity> peliculas = peliculaRepository.findAll();
        return peliculaMapper.toListPeliculaDTO(peliculas);
    }

    // Get by Id
    public PeliculaDTO getPelicula(Long id) {
        PeliculaEntity pelicula = peliculaRepository.getReferenceById(id);
        return peliculaMapper.tpPeliculaEntity2DTO(pelicula);
    }

    // Creation
    public PeliculaDTO savePelicula(PeliculaDTO peliculaDTO) {
        if (validadorDTO.peliculaDTOvalida(peliculaDTO)) {
            PeliculaEntity pelicula = peliculaMapper.toPeliculaDTO2entity(peliculaDTO);
            Set<PersonajesEntity> personajes = pelicula.getPersonajes();
            Set<GeneroEntity> genero = pelicula.getGenero();
            pelicula.setPersonajes(filtroPersonaje(personajes, false));
            pelicula.setGenero(filtroGenero(genero, false));
            pelicula = peliculaRepository.save(pelicula);
            asignarPersonajeExistente(filtroPersonaje(personajes, true), pelicula.getId());
            asignarGeneroExistente(filtroGenero(genero, true), pelicula.getId());

            return peliculaMapper.tpPeliculaEntity2DTO(pelicula);
        } else {
            throw new PeliculaException(ErrorMensajes.DTO_INCORRECTO_DATA);
        }
    }

    private Set<PersonajesEntity> filtroPersonaje(Set<PersonajesEntity> personajes, boolean existing) {
        Set<PersonajesEntity> res = new HashSet<>();
        for (PersonajesEntity personaje : personajes) {
            if (personaje.getId() == null) {
                if (!existing)
                    res.add(personaje);
            } else {
                if (personajeRepository.existsById(personaje.getId())) {
                    if (existing)
                        res.add(personaje);
                } else {
                    throw new PeliculaException(ErrorMensajes.PELICULA_NO_ENCONTRADA);
                }
            }
        }
        return res;
    }

    private void asignarPersonajeExistente(Set<PersonajesEntity> personajes, Long peliculaID) {
        for (PersonajesEntity personaje : personajes) {
            addPersonaje2Pelicula(personaje.getId(), peliculaID);
        }
    }

    private Set<GeneroEntity> filtroGenero(Set<GeneroEntity> generos, boolean existing) {
        Set<GeneroEntity> res = new HashSet<>();
        for (GeneroEntity genero : generos) {
            if (genero.getId() == null) {
                if (!existing && generoRepository.findByName(genero.getNombre()).isEmpty())
                    res.add(genero);
                else if (existing && !generoRepository.findByName(genero.getNombre()).isEmpty())
                    res.add(generoRepository.findByName(genero.getNombre()).get(0));
            } else {
                if (generoRepository.existsById(genero.getId())) {
                    if (existing)
                        res.add(genero);
                } else {
                    throw new PeliculaException(ErrorMensajes.GENERO_NO_ENCONTRADO);
                }
            }
        }
        return res;
    }

    private void asignarGeneroExistente(Set<GeneroEntity> generos, Long peliculaID) {
        for (GeneroEntity genero : generos) {
            addGenero2Pelicula(genero.getId(), peliculaID);
        }
    }

    // Update
    public PeliculaDTO updatePelicula(PeliculaDTO peliculaDTO) {
        if (peliculaRepository.existsById(peliculaDTO.getId())) {
            if (validadorDTO.peliculaDTOupdateValida(peliculaDTO)) {
                PeliculaEntity pelicula = peliculaRepository.getReferenceById(peliculaDTO.getId());
                pelicula.setId(peliculaDTO.getId());
                pelicula.setImagen(peliculaDTO.getImagen());
                pelicula.setNombre(peliculaDTO.getImagen());
                pelicula.setRating(peliculaDTO.getRating());
                pelicula.setFechaCreacion(peliculaDTO.getFechaCreacion());
                pelicula = peliculaRepository.save(pelicula);
                return peliculaMapper.tpPeliculaEntity2DTO(pelicula);
            } else {
                throw new PeliculaException(ErrorMensajes.DTO_INCORRECTO_DATA);
            }
        } else {
            throw new PeliculaException(ErrorMensajes.PELICULA_NO_ENCONTRADA);
        }
    }

    // Delete
    public void deletePelicula(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMensajes.PELICULA_NO_ENCONTRADA);
        }
        peliculaRepository.deleteById(id);
    }

    // Add character to a movie
    public void addPersonaje2Pelicula(Long personajeID, Long peliculaID) {
        if (personajeRepository.existsById(personajeID) &&
                peliculaRepository.existsById(peliculaID)) {
            PeliculaEntity pelicula = peliculaRepository.getReferenceById(peliculaID);
            PersonajesEntity personaje = personajeRepository.getReferenceById(personajeID);
            Set<PersonajesEntity> personajes = pelicula.getPersonajes();
            personajes.add(personaje);
            pelicula.setPersonajes(personajes);
            peliculaRepository.save(pelicula);
        } else if (!personajeRepository.existsById(personajeID)) {
            throw new EntityNotFoundException(ErrorMensajes.PERSONAJE_NO_ENCONTRADO);
        } else if (!peliculaRepository.existsById(peliculaID)) {
            throw new EntityNotFoundException(ErrorMensajes.PELICULA_NO_ENCONTRADA);
        }
    }

    // Remover personaje de una pelicula


    public void removerPersonajeToPelicula(Long personajeID, Long peliculaID) {
        if (personajeRepository.existsById(personajeID) &&
                peliculaRepository.existsById(peliculaID)) {
            PeliculaEntity pelicula = peliculaRepository.getReferenceById(peliculaID);
            PersonajesEntity personaje = personajeRepository.getReferenceById(personajeID);
            Set<PersonajesEntity> personajes = pelicula.getPersonajes();
            personajes.remove(personaje);
            pelicula.setPersonajes(personajes);
            peliculaRepository.save(pelicula);
        } else if (!personajeRepository.existsById(personajeID)) {
            throw new EntityNotFoundException(ErrorMensajes.PERSONAJE_NO_ENCONTRADO);
        } else if (!peliculaRepository.existsById(peliculaID)) {
            throw new EntityNotFoundException(ErrorMensajes.PELICULA_NO_ENCONTRADA);
        }
    }

    // Add genre to a movie
    public void addGenero2Pelicula(Long generoID, Long peliculaID) {
        if (generoRepository.existsById(generoID) &&
                peliculaRepository.existsById(peliculaID)) {
            PeliculaEntity pelicula = peliculaRepository.getReferenceById(peliculaID);
            GeneroEntity genero = generoRepository.getReferenceById(generoID);
            Set<GeneroEntity> generos = pelicula.getGenero();
            generos.add(genero);
            pelicula.setGenero(generos);
            peliculaRepository.save(pelicula);
        } else if (!generoRepository.existsById(generoID)) {
            throw new EntityNotFoundException(ErrorMensajes.GENERO_NO_ENCONTRADO);
        } else if (!peliculaRepository.existsById(peliculaID)) {
            throw new EntityNotFoundException(ErrorMensajes.PELICULA_NO_ENCONTRADA);
        }
    }

    // Remove genre from a movie
    public void removerGenero2Pelicula(Long generoID, Long peliculaID) {
        if (generoRepository.existsById(generoID) &&
                peliculaRepository.existsById(peliculaID)) {
            PeliculaEntity pelicula = peliculaRepository.getReferenceById(peliculaID);
            GeneroEntity genero = generoRepository.getReferenceById(generoID);
            Set<GeneroEntity> generos = pelicula.getGenero();
            generos.remove(genero);
            pelicula.setGenero(generos);
            peliculaRepository.save(pelicula);
        } else if (!generoRepository.existsById(generoID)) {
            throw new EntityNotFoundException(ErrorMensajes.GENERO_NO_ENCONTRADO);
        } else if (!peliculaRepository.existsById(peliculaID)) {
            throw new EntityNotFoundException(ErrorMensajes.PELICULA_NO_ENCONTRADA);
        }
    }

    // GetByFilters
    public List<PeliculaDTO> getPeliculasByFiltros(String nombre, Set<Long> genero, String orden) {
        List<PeliculaEntity> peliculas = peliculaRepository.findAll(
                peliculaEspecificacion.getByFilters(nombre, genero, orden));
        return peliculaMapper.toListPeliculaDTO(peliculas);

    }
}
