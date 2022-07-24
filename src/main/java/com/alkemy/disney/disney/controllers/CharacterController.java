package com.alkemy.disney.disney.controllers;


import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    //lista basica, debe devolver solo imagen y nombre
    //GET/characters
    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getAllCharactersLite() {
        List<CharacterBasicDTO> charactersBasicList = characterService.getAllCharactersBasic();
        return ResponseEntity.ok(charactersBasicList);
    }

    //4. CRUD
    //POST /characters
    @PostMapping
    public ResponseEntity<CharacterDTO> postNewCharacter(@Valid @RequestBody CharacterDTO characterDto) {
        CharacterDTO savedCharacter = characterService.saveNewCharacter(characterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    //PUT /characters/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacterById(@PathVariable Long id, @RequestBody CharacterDTO characterDto) {
        CharacterDTO updatedCharacter = characterService.updateCharacter(id, characterDto);
        return ResponseEntity.ok().body(updatedCharacter);
    }

    //DELETE /characters/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacterById(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Personaje completo, con pelicula asociada.
    //GET /characters/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharactersDetailsById(@PathVariable Long id) {
        CharacterDTO characterDto = characterService.getCharacterDetailsById(id);
        return ResponseEntity.ok(characterDto);
    }

    //Burcar personajes por filtros
    // GET /characters/filters?name=name
    // GET /characters/filters?age=age
    // GET /characters/filters?movies=movieId
    @GetMapping("/filters")
    public ResponseEntity<List<CharacterDTO>> getCharacterDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies
    ) {
        List<CharacterDTO> characterDTOList = characterService.getCharactersByFilters(name, age, movies);
        return ResponseEntity.ok(characterDTOList);
    }
}
