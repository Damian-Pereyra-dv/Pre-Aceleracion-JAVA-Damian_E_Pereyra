package com.alkemy.disney.disney.controllers;

import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.service.PersonajeService;
import com.alkemy.disney.disney.service.implement.PersonajeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("personajes")
public class PersonajeController {

    @Autowired
    private PersonajeServiceImpl personajeService;



    // GetAll
    @GetMapping(path = "/all")
    public ResponseEntity<List<PersonajeDTO>> getAllCharacters() {
        List<PersonajeDTO> characters = personajeService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }



    //Creacion PJ
    @PostMapping
    public ResponseEntity<PersonajeDTO> savePersonaje(@RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO character = personajeService.savePersonaje(personajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }

    //Character update
    @PutMapping
    public ResponseEntity<PersonajeDTO> updatePersonaje(@RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO personaje = personajeService.updatePersonaje(personajeDTO);
        return ResponseEntity.ok(personaje);
    }

    //Character elimination
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) throws SQLException {
        personajeService.deletePersonaje(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    // GetById
    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonajeDTO> getPersonaje(@PathVariable Long id) {
        PersonajeDTO personaje = personajeService.getPersonaje(id);
        return ResponseEntity.ok(personaje);
    }



    // GetByFilters
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getPersonajesPorFiltro (
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Set<Long> peliculas) {
        List<PersonajeDTO> characters = personajeService.getPersonajeFiltrado(nombre, edad, peliculas);
        return ResponseEntity.ok(characters);
    }
}
