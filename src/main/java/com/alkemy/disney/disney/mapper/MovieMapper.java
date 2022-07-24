package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;


@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    /* Entity to BasicDTO */
    public MovieBasicDTO movieEntity2BasicDTO(MovieEntity entity) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        return dto;
    }

    /* List<Entity> to List<BasicDTO> */
    public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entityList) {
        List<MovieBasicDTO> basicDTOList = new ArrayList<>();
        for (MovieEntity entity : entityList) {
            basicDTOList.add(movieEntity2BasicDTO(entity));
        }
        return basicDTOList;
    }

    /* DTO to Entity */
    public MovieEntity movieDTO2Entity(MovieDTO movieDTO) {
        MovieEntity entity = new MovieEntity();

        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setCreationDate(this.string2LocalDate(movieDTO.getCreationDate()));
        entity.setRating(movieDTO.getRating());
        entity.setGenreId((movieDTO.getGenreId()));

        List<CharacterEntity> characters = this.characterMapper.characterDTOList2EntityList(movieDTO.getCharacters());
        entity.setCharacters(characters);
        return entity;
    }

    /* Entity to DTO */
    public MovieDTO movieEntity2DTO(MovieEntity movieEntity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movieEntity.getId());
        dto.setImage(movieEntity.getImage());
        dto.setTitle(movieEntity.getTitle());
        dto.setCreationDate(movieEntity.getCreationDate().toString());
        dto.setRating(movieEntity.getRating());
        dto.setGenreId(movieEntity.getGenreId());

        if (loadCharacters) {
            List<CharacterDTO> characters = this.characterMapper.characterEntityList2DTOList(movieEntity.getCharacters(), false);
            dto.setCharacters(characters);
        }
        return dto;
    }

    /* List<Entity> to List<DTO> */
    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
        List<MovieDTO> dtos = new ArrayList<>();

        for (MovieEntity entity : entities) {
            dtos.add(movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }

    /* String to LocalDate converter */
    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(stringDate, formatter);
    }

    public void movieEntityRefreshValues(MovieEntity entity, MovieDTO movieDTO) {
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setCreationDate(string2LocalDate(movieDTO.getCreationDate()));
        entity.setRating(movieDTO.getRating());
        entity.setGenreId(movieDTO.getGenreId());
        entity.setCharacters(characterMapper.characterDTOList2EntityList(movieDTO.getCharacters()));

    }
}
