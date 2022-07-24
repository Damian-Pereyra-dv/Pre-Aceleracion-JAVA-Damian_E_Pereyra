package com.alkemy.disney.disney.service.Impl;


import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.exception.msj.ErrorMessages;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;

    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> genreEntities = genreRepository.findAll();
        return genreMapper.genreEntityList2DTOList(genreEntities);
    }

    @Override
    public GenreDTO getGenreById(Long genreId) {
        Optional<GenreEntity> genreEntity = this.genreRepository.findById(genreId);
        if (!genreEntity.isPresent()) {
            throw new ParamNotFound(ErrorMessages.GENRE_ID_INVALID);
        }
        return genreMapper.genreEntity2DTO(genreEntity.get());
    }

    public GenreDTO saveGenreToDB(GenreDTO genreDto) {
        GenreEntity genreEntity = genreMapper.genreDTO2Entity(genreDto);
        GenreEntity savedEntity = genreRepository.save(genreEntity);
        return genreMapper.genreEntity2DTO(savedEntity);
    }


    public GenreDTO updateGenreInDB(Long id, GenreDTO genreNewData) {
        Optional<GenreEntity> entity = genreRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound(ErrorMessages.GENRE_ID_INVALID);
        }
        genreMapper.genreEntityRefreshValues(entity.get(), genreNewData);
        GenreEntity entitySaved = genreRepository.save(entity.get());
        return genreMapper.genreEntity2DTO(entitySaved);

    }

    public void deleteGenreInDB(Long id) {
        this.genreRepository.deleteById(id);
    }

}