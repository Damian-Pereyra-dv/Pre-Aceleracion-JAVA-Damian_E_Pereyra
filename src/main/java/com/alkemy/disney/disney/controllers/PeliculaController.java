package com.alkemy.disney.disney.controllers;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.service.GeneroService;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.implement.PeliculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("peliculas")

public class PeliculaController {

    @Autowired
    private PeliculaServiceImpl peliculaService;



    // Get all
    @GetMapping(path = "/all")
    public ResponseEntity<List<PeliculaDTO>> getAllPeliculas() {
        List<PeliculaDTO> peliculas = peliculaService.getAllPeliculas();
        return ResponseEntity.ok(peliculas);
    }



    // Get by Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<PeliculaDTO> getMovie(@PathVariable Long id) {
        PeliculaDTO pelicula = peliculaService.getPelicula(id);
        return ResponseEntity.ok(pelicula);
    }



    // Creacion
    @PostMapping
    public ResponseEntity<PeliculaDTO> savePelicula(@RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO pelicula = peliculaService.savePelicula(peliculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
    }

    // Update
    @PutMapping
    public ResponseEntity<PeliculaDTO> updatePelicula(@RequestBody PeliculaDTO peliculaDTO) throws SQLException {
        PeliculaDTO pelicula = peliculaService.updatePelicula(peliculaDTO);
        return ResponseEntity.ok(pelicula);
    }

    // Delete
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.ok().build();
    }

    // Add character to a movie
    @PostMapping(path = "/{peliculaID}/character/{personajeID}")
    public ResponseEntity<Void> addPersonaje2Pelicula(@PathVariable Long personajeID, @PathVariable Long peliculaID) {
        peliculaService.addPersonaje2Pelicula(personajeID, peliculaID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Remove character from a movie
    @DeleteMapping(path = "/{peliculaID}/personaje/{personajeID}")
    public ResponseEntity<Void> removerPersonaje2Pelicula(@PathVariable Long personajeID, @PathVariable Long peliculaID) {
        peliculaService.removerPersonajeToPelicula(personajeID, peliculaID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Add genre to a movie
    @PostMapping(path = "/{peliculaID}/genero/{generoID}")
    public ResponseEntity<Void> addGenero2Pelicula(@PathVariable Long generoID, @PathVariable Long peliculaID) {
        peliculaService.addGenero2Pelicula(generoID, peliculaID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Remove genre from a movie
    @DeleteMapping(path = "/{peliculaID}/genero/{generoID}")
    public ResponseEntity<Void> removerGenero2Pelicula(@PathVariable Long generoID, @PathVariable Long peliculaID) {
        peliculaService.removerGenero2Pelicula(generoID, peliculaID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    // GetByFilters
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getMoviesByFilters(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Set<Long> genero,
            @RequestParam(required = false, defaultValue = "ASC") String orden) {
        List<PeliculaDTO> peliculas = peliculaService.getPeliculasByFiltros(nombre, genero, orden);
        return ResponseEntity.ok(peliculas);
    }
}
