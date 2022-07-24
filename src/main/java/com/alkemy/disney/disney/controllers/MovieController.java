package com.alkemy.disney.disney.controllers;


import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    //Lista basica de pelicula, solo imagen titulo y fecha
    //GET /movies
    public ResponseEntity<List<MovieBasicDTO>> getAllMoviesLite() {
        List<MovieBasicDTO> moviesListLite = movieService.getAllMoviesLite();
        return ResponseEntity.ok(moviesListLite);
    }

    // busca Detalles de la pelicula con todos sus campos incluyendo personajes asociados
    //GET /movies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieDetailsById(@PathVariable Long id) {
        MovieDTO movieDto = movieService.getMovieDetailsById(id);
        return ResponseEntity.ok(movieDto);
    }

    //crud
    //POST /movies
    @PostMapping
    public ResponseEntity<MovieDTO> postNewMovie(@RequestBody MovieDTO movieDto) {
        MovieDTO savedMovie = movieService.saveNewMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    //POST /movies/{movieId}/character/{characterId}
    @PostMapping("/{movieId}/character/{characterId}")
    public ResponseEntity<Void> addCharacterToMovie(@PathVariable Long movieId, @PathVariable Long characterId) {
        movieService.addCharacterToMovie(movieId, characterId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //PUT /movies/{id} (actualiza por ID)
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovieById(@PathVariable Long id, @RequestBody MovieDTO movieNewData) {
        MovieDTO updatedMovie = movieService.updateMovieById(id, movieNewData);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedMovie);
    }

    //DELETE /movies/{id} (elimina por byId)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //DELETE /movies/{genreId}/character/{characterId} (elimina personaje de pelicula por ID)
    @DeleteMapping("/{movieId}/character/{characterId}")
    public ResponseEntity<Void> removeCharacterFromMovie(@PathVariable Long movieId, @PathVariable Long characterId) {
        movieService.deleteCharacterFromMovie(movieId, characterId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //busca pelicula por filtro
    // /movies/filters?title=name
    // /movies/filters?genre=genderId
    // /movies/filters?order=ASC | DESC
    @GetMapping("/filters")
    public ResponseEntity<List<MovieDTO>> getMoviesDetailsByFilters (
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    )
    { List<MovieDTO> filteredMovies = movieService.getMoviesByFilters(title, genre, order);
        return ResponseEntity.ok(filteredMovies);
    }

}