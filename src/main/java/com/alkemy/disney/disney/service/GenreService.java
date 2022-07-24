package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    //GET
    List<GenreDTO> getAllGenres();

    GenreDTO getGenreById(Long genreId);

    //SAVE
    GenreDTO saveGenreToDB(GenreDTO genreDto);

    //UPDATE
    GenreDTO updateGenreInDB(Long id, GenreDTO genreNewData);

    //DELETE
    void deleteGenreInDB(Long id);
}