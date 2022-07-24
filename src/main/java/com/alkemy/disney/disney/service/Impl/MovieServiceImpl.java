package com.alkemy.disney.disney.service.Impl;


import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.exception.msj.ErrorMessages;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.spec.MovieSpecifications;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private MovieSpecifications movieSpecifications;

    //GET
    public List<MovieBasicDTO> getAllMoviesLite() {
        List<MovieEntity> entitiesList = movieRepository.findAll();
        return movieMapper.movieEntityList2BasicDTOList(entitiesList);
    }

    public MovieDTO getMovieDetailsById(Long id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if (!movieEntity.isPresent()) {
            throw new ParamNotFound(ErrorMessages.MOVIE_ID_INVALID);
        }
        return movieMapper.movieEntity2DTO(movieEntity.get(), true);
    }

    //SAVE
    public MovieDTO saveNewMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity entitySaved = movieRepository.save(movieEntity);
        return movieMapper.movieEntity2DTO(entitySaved, true);
    }

    //UPDATE
    public MovieDTO updateMovieById(Long id, MovieDTO movieNewData) {

        Optional<MovieEntity> entity = this.movieRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound(ErrorMessages.MOVIE_ID_INVALID);
        }
        this.movieMapper.movieEntityRefreshValues(entity.get(), movieNewData);
        MovieEntity entitySaved = this.movieRepository.save(entity.get());
        return this.movieMapper.movieEntity2DTO(entitySaved, false);

    }

    //DELETE
    public void deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new ParamNotFound(ErrorMessages.MOVIE_NOT_FOUND);
        }
    }

    public void deleteCharacterFromMovie(Long movieId, Long characterId) {
        MovieEntity movie = getMovieEntityById(movieId);
        movie.getCharacters().size();
        CharacterEntity characterEntity = characterService.getCharacterEntityById(characterId);
        movie.removeCharacterFromMovie(characterEntity);
        movieRepository.save(movie);

    }

    //Filters
    public List<MovieDTO> getMoviesByFilters(String title, String genre, String order) {
        MovieFiltersDTO movieFiltersDto = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> movieEntities = movieRepository.findAll(movieSpecifications.getByFilters(movieFiltersDto));
        return movieMapper.movieEntityList2DTOList(movieEntities, true);
    }

    // Aditional Methods
    public MovieEntity getMovieEntityById(Long movieId) {
        Optional<MovieEntity> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()) {
            throw new ParamNotFound(ErrorMessages.MOVIE_NOT_FOUND);
        }
        return movie.get();
    }

    public void addCharacterToMovie(Long movieId, Long characterId) {
        MovieEntity movie = this.getMovieEntityById(movieId);
        movie.getCharacters().size();
        CharacterEntity characterEntity = characterService.getCharacterEntityById(characterId);
        movie.addCharacterToMovie(characterEntity);
        movieRepository.save(movie);
    }
}
