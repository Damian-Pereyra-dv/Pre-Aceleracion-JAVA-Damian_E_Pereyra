package com.alkemy.disney.disney.service.implement;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.exception.GeneroException;
import com.alkemy.disney.disney.exception.msj.ErrorMensajes;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.service.GeneroService;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.validador.ValidadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.util.List;
import java.util.Set;

@Service
public class GeneroServiceImpl  {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private ValidadorDTO validadorDTO;

    @Autowired
    private PeliculaServiceImpl peliculaService;

    public GeneroDTO save(GeneroDTO generoDTO) {
        if (validadorDTO.generoDTOvalido(generoDTO)) {
            GeneroEntity genero = generoMapper.toGeneroDTO2Entity(generoDTO);
            genero = generoRepository.save(genero);
            return generoMapper.toGeneroEntity2DTO(genero);
        } else {
            throw new GeneroException(ErrorMensajes.DTO_INCORRECTO_DATA);
        }
    }

    public GeneroDTO updateGenero(GeneroDTO generoDTO) {
        if (generoRepository.existsById(generoDTO.getId())){
            if (validadorDTO.generoDTOvalido(generoDTO)) {
                GeneroEntity genero = generoMapper.toGeneroDTO2Entity(generoDTO);
                genero = generoRepository.save(genero);
                return generoMapper.toGeneroEntity2DTO(genero);
            } else {
                throw new GeneroException(ErrorMensajes.DTO_INCORRECTO_DATA);
            }
        } else {
            throw new GeneroException(ErrorMensajes.GENERO_NO_ENCONTRADO);
        }
    }

    public void deleteGenero(Long id) {
        if (generoRepository.existsById(id)) {
            sinGenero(id);
            generoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(ErrorMensajes.GENERO_NO_ENCONTRADO);
        }
    }

    private void sinGenero(Long id){

        GeneroEntity genero = generoRepository.getReferenceById(id);
        if (!genero.getPeliculas().isEmpty()) {
            Set<PeliculaEntity> peliculas = genero.getPeliculas();
            for (PeliculaEntity pelicula : peliculas) {
                peliculaService.removerGenero2Pelicula(genero.getId(), pelicula.getId());
            }
        }


    }

    public GeneroDTO getGenero (Long id) {
        if (generoRepository.existsById(id)) {
            GeneroEntity genero = generoRepository.getReferenceById(id);
            return generoMapper.toGeneroEntity2DTO(genero);
        } else {
            throw new EntityNotFoundException(ErrorMensajes.GENERO_NO_ENCONTRADO);
        }
    }

    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> genres = generoRepository.findAll();
        return generoMapper.toListGeneroDto(genres);
    }



}
