package com.alkemy.disney.disney.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Integer rating;
    private Long genreId;
    private List<CharacterDTO> characters;

}