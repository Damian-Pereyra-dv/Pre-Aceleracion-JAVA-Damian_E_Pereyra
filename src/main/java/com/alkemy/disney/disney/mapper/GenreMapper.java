package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO genreDto) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(genreDto.getImage());
        genreEntity.setName(genreDto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity genreEntity) {
        GenreDTO genreDto = new GenreDTO();
        genreDto.setId(genreEntity.getId());
        genreDto.setImage(genreEntity.getImage());
        genreDto.setName(genreEntity.getName());
        return genreDto;

    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> genreEntityList) {
        List<GenreDTO> genreDtoList = new ArrayList<>();

        for (GenreEntity entity : genreEntityList) {
            genreDtoList.add(this.genreEntity2DTO(entity));
        }
        return genreDtoList;
    }


    public void genreEntityRefreshValues(GenreEntity entity, GenreDTO dto) {
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
    }
}

