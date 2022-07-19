package com.alkemy.disney.disney.controllers;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.service.GeneroService;
import com.alkemy.disney.disney.service.implement.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoService;

    // Create
    @PostMapping
    public ResponseEntity<GeneroDTO> saveGenero(@RequestBody GeneroDTO generoDTO) {
        GeneroDTO genero = generoService.save(generoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genero);
    }

    // Update
    @PutMapping
    public ResponseEntity<GeneroDTO> updateGenre(@RequestBody GeneroDTO generoDTO) {
        GeneroDTO genero = generoService.updateGenero(generoDTO);
        return ResponseEntity.ok(genero);
    }

    // Delete
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) throws SQLException {
        generoService.deleteGenero(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Get by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<GeneroDTO> getGenre(@PathVariable Long id) {
        GeneroDTO genre = generoService.getGenero(id);
        return ResponseEntity.ok(genre);
    }

    // Get all
    @GetMapping(path = "/all")
    public ResponseEntity<List<GeneroDTO>> getAllGeneros() {
        List<GeneroDTO> generos = generoService.getAllGeneros();
        return ResponseEntity.ok(generos);
    }



}
