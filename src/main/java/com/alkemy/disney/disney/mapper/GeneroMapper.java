package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public GeneroEntity generoDTO2Entity (GeneroDTO dto) {

        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImagen(dto.getImagen());
        generoEntity.setNombre(dto.getNombre());
        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO (GeneroEntity entity){
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());


        return dto;


    }


}
