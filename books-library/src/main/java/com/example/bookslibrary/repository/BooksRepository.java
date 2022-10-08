package com.example.bookslibrary.repository;
import com.example.bookslibrary.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BookEntity,Long> {
}

