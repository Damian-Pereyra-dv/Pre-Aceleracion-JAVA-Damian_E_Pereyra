package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {
    //GET
    CharacterEntity getCharacterEntityById(Long characterId);

    List<CharacterBasicDTO> getAllCharactersBasic();

    CharacterDTO getCharacterDetailsById(Long characterId);

    //GET BY FILTERS
    List<CharacterDTO> getCharactersByFilters(String name, Integer age, Set<Long> movies);

    //SAVE
    CharacterDTO saveNewCharacter(CharacterDTO characterDTO);

    //UPDATE
    CharacterDTO updateCharacter(Long id, CharacterDTO character);

    //DELETE
    void deleteCharacter(Long id);


}
