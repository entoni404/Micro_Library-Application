package com.example.genrelibrary.genrelibrary.manager;

import com.example.genrelibrary.genrelibrary.model.GenreEntity;
import com.example.genrelibrary.genrelibrary.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component @RequiredArgsConstructor public class GenreManager {
    private final GenreRepository localRepository;
    
    public List<GenreEntity> getAllGenres() {
        return localRepository.findAll();
    }
    
    public GenreEntity getGenreById(Long id) {
        return localRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Resource not found"));
    }
    
    public void createOrUpdate(GenreEntity genre) {
        if (localRepository.existsByTitle(genre.getTitle())) {
            genre = localRepository.findByTitle(genre.getTitle()).get();
        }
        localRepository.save(genre);
    }
    
    public void deleteGenre(Long id) {
        if (!localRepository.existsById(id)) {
            throw new NoSuchElementException("Resource not found");
        }
        localRepository.deleteById(id);
    }
}
