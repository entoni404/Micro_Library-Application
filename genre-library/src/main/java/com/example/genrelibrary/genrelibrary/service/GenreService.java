package com.example.genrelibrary.genrelibrary.service;

import com.example.genrelibrary.genrelibrary.manager.GenreManager;
import com.example.genrelibrary.genrelibrary.model.Genre;
import com.example.genrelibrary.genrelibrary.model.GenreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor public class GenreService {
    
    private final GenreManager genreManager;
    
    public List<Genre> getAllGenres() {
        return genreManager.getAllGenres().stream().map(g -> new Genre(g.getId(), g.getTitle()))
                .collect(Collectors.toList());
    }
    
    public Genre getGenreById(Long id) {
        GenreEntity genreEntity = genreManager.getGenreById(id);
        return new Genre(genreEntity.getId(), genreEntity.getTitle());
    }
    
    public void createGenre(Genre genre) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(genreEntity.getId());
        genreEntity.setTitle(genre.getTitle());
        genreManager.createOrUpdate(genreEntity);
    }
    
    public void deleteGenre(Long id) {
        genreManager.deleteGenre(id);
    }
}


