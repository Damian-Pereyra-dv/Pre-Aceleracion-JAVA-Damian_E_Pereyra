package com.alkemy.disney.disney.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<MovieDTO> movies;
}