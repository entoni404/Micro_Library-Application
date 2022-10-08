package com.example.bookslibrary.manager;

import com.example.bookslibrary.model.BookEntity;
import com.example.bookslibrary.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service @RequiredArgsConstructor public class BooksManager {
    
    private final BooksRepository booksRepository;
    
    public List<BookEntity> getAllBooks() {
        return booksRepository.findAll();
    }
    
    public BookEntity getBookById(Long id) {
        return booksRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("The resource you are looking for does not exist"));
    }
    
    public BookEntity createBook(BookEntity book) {
        return booksRepository.save(book);
    }
    
    public void deleteBook(Long id) {
        booksRepository.deleteById(id);
    }
    
    public void updateBook(BookEntity newBook, Long id) {
        BookEntity oldBook = getBookById(id);
        oldBook.setTitle(newBook.getTitle());
        oldBook.setPagesNum(newBook.getPagesNum());
        oldBook.setPrice(newBook.getPrice());
        oldBook.setLanguage(newBook.getLanguage());
        oldBook.setDescription(newBook.getDescription());
        oldBook.setAuthorId(newBook.getAuthorId());
        oldBook.setGenreId(newBook.getGenreId());
        booksRepository.save(oldBook);
    }
}
