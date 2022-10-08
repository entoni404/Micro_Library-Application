package com.example.genrelibrary.genrelibrary.controller;

import com.example.genrelibrary.genrelibrary.model.Genre;
import com.example.genrelibrary.genrelibrary.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping(value = "/genre",
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GenreController {
    
    private GenreService genreService;

    @GetMapping()
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGenreById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(genreService.getGenreById(id));
        } catch (NoSuchElementException notFound) {
            return ResponseEntity.notFound().build();
        } catch (Exception serverError) {
            return ResponseEntity.internalServerError().body(serverError.getMessage());
        }
    }

    @PostMapping()
    public String saveGenre(@RequestBody Genre genre) {
        genreService.createGenre(genre);
        return "Genre added";
    }

    @DeleteMapping("/{id}")
    public String deleteGenre( @PathVariable Long id){
        genreService.deleteGenre(id);
        return "The Genre is Deleted";
    }
}
