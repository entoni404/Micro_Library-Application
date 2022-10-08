package com.example.bookslibrary.service;

import com.example.bookslibrary.manager.BooksManager;
import com.example.bookslibrary.model.Book;
import com.example.bookslibrary.model.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor public class BookService {
    private final BooksManager booksManager;
    private final RestService restService;
    
    public List<Book> getList() {
        return booksManager.getAllBooks().stream().map(BookEntity::getId).map(this::getByID)
                .collect(Collectors.toList());
    }
    
    public Book getByID(Long id) {
        BookEntity bookEntity = booksManager.getBookById(id);
        Book book = mapToBook(bookEntity);
        book.setAuthor(restService.getAuthor(bookEntity.getAuthorId()));
        book.setGenre(restService.getGenre(bookEntity.getGenreId()));
        return book;
    }
    
    public void createBook(Book book) {
        BookEntity bookEntity = mapToBookEntity(book);
        booksManager.createBook(bookEntity);
    }
    
    public void deleteBook(Long id) {
        booksManager.deleteBook(id);
    }
    
    public void updateBook(Book updatedBook, Long id) {
        booksManager.updateBook(mapToBookEntity(updatedBook), id);
    }
    private Book mapToBook(BookEntity source) {
        Book book = new Book();
        book.setId(source.getId());
        book.setDescription(source.getDescription());
        book.setTitle(source.getTitle());
        book.setLanguage(source.getLanguage());
        book.setPrice(source.getPrice());
        book.setPagesNum(source.getPagesNum());
        return book;
    }
    
    private BookEntity mapToBookEntity(Book source) {
        BookEntity book = new BookEntity();
        book.setDescription(source.getDescription());
        book.setTitle(source.getTitle());
        book.setLanguage(source.getLanguage());
        book.setPrice(source.getPrice());
        book.setPagesNum(source.getPagesNum());
        
        if(source.getAuthor() != null) {
            book.setAuthorId(source.getAuthor().getId());
        }
        if(source.getGenre() != null) {
            book.setAuthorId(source.getGenre().getId());
        }
        
        return book;
    }
}
