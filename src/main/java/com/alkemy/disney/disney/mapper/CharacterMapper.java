package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;


@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    /* List<DTO> to List<Entity>*/
    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos) {
        List<CharacterEntity> entities = new ArrayList<>();

        for (CharacterDTO dto : dtos) {
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }

    /* DTO to Entity */
    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());

        return characterEntity;
    }

    /* List<Entity> to List<DTO> */
    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovies) {
        List<CharacterDTO> dtos = new ArrayList<>();

        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    /* Entity to DTO */
    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(entity.getId());
        characterDTO.setImage(entity.getImage());
        characterDTO.setName(entity.getName());
        characterDTO.setAge(entity.getAge());
        characterDTO.setWeight(entity.getWeight());
        characterDTO.setHistory(entity.getHistory());
        if (loadMovies) {
            List<MovieDTO> moviesDTOList = movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            characterDTO.setMovies(moviesDTOList);
        }
        return characterDTO;
    }

    /* List<Entity> to List<BasicDTO>*/
    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entitiesList) {
        List<CharacterBasicDTO> dtoBasicList = new ArrayList<>();
        CharacterBasicDTO basicDto;
        for (CharacterEntity entity : entitiesList) {
            basicDto = new CharacterBasicDTO();
            basicDto.setImage(entity.getImage());
            basicDto.setName(entity.getName());
            dtoBasicList.add(basicDto);
        }
        return dtoBasicList;
    }

    /* Character entity refresh values */
    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO characterDto) {
        entity.setImage(characterDto.getImage());
        entity.setName(characterDto.getName());
        entity.setAge(characterDto.getAge());
        entity.setWeight(characterDto.getWeight());
        entity.setHistory(characterDto.getHistory());

    }
}
