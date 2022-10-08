package com.example.genrelibrary.genrelibrary.repository;

import com.example.genrelibrary.genrelibrary.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<GenreEntity,Long> {
    boolean existsByTitle(String title);
    boolean existsById(Long id);
    
    Optional<GenreEntity> findByTitle(String ttile);
}
