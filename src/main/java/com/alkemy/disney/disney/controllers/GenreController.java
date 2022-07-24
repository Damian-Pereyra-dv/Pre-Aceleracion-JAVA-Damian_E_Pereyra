package com.alkemy.disney.disney.controllers;


import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    //GET /genres
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genreDtoList = genreService.getAllGenres();
        return ResponseEntity.ok().body(genreDtoList);
    }

    //GET /genres/{id}
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        GenreDTO genreDto = genreService.getGenreById(id);
        return ResponseEntity.ok().body(genreDto);
    }

    //POST /genres
    @PostMapping
    public ResponseEntity<GenreDTO> postNewGenre(@Valid @RequestBody GenreDTO genreDto) {
        GenreDTO savedGenreFromDB = genreService.saveGenreToDB(genreDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenreFromDB);
    }

    //PUT /genres/{id}
    @PutMapping("{id}")
    public ResponseEntity<GenreDTO> updateGenreById(@PathVariable Long id, @RequestBody GenreDTO genreDto) {
        GenreDTO updatedGenre = genreService.updateGenreInDB(id, genreDto);
        return ResponseEntity.ok().body(updatedGenre);
    }

    //DELETE /genres/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable Long id) {
        genreService.deleteGenreInDB(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}