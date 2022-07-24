package com.alkemy.disney.disney.service.Impl;


import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;

import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;

import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.exception.msj.ErrorMessages;
import com.alkemy.disney.disney.mapper.CharacterMapper;

import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.spec.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterSpecification characterSpecification;


    public CharacterEntity getCharacterEntityById(Long characterId) {
        Optional<CharacterEntity> character = characterRepository.findById(characterId);
        if (!character.isPresent()) {
            throw new ParamNotFound(ErrorMessages.CHARACTER_ID_INVALID);
        }
        return character.get();
    }

    public List<CharacterBasicDTO> getAllCharactersBasic() {
        List<CharacterEntity> characterEntitiesBasicList = characterRepository.findAll();
        return characterMapper.characterEntityList2BasicDTOList(characterEntitiesBasicList);
    }

    public CharacterDTO getCharacterDetailsById(Long characterId) {
        Optional<CharacterEntity> character = characterRepository.findById(characterId);
        if (!character.isPresent()) {
            throw new ParamNotFound(ErrorMessages.CHARACTER_ID_INVALID);
        }
        return characterMapper.characterEntity2DTO(character.get(), true);
    }

    public CharacterDTO saveNewCharacter(CharacterDTO characterDTO) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(characterDTO);
        CharacterEntity entitySaved = characterRepository.save(entity);
        return characterMapper.characterEntity2DTO(entitySaved, false);

    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    public CharacterDTO updateCharacter(Long id, CharacterDTO characterNewData) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound(ErrorMessages.CHARACTER_ID_INVALID);
        }
        characterMapper.characterEntityRefreshValues(entity.get(), characterNewData);
        CharacterEntity entitySaved = characterRepository.save(entity.get());
        return characterMapper.characterEntity2DTO(entitySaved, false);
    }

    public List<CharacterDTO> getCharactersByFilters(String name, Integer age, Set<Long> movies) {
        CharacterFiltersDTO characterFiltersDto = new CharacterFiltersDTO(name, age, movies);
        List<CharacterEntity> entitiesList = characterRepository.findAll(characterSpecification.getByFilters(characterFiltersDto));
        return characterMapper.characterEntityList2DTOList(entitiesList, true);
    }
}
