package com.example.bookslibrary.controller;

import com.example.bookslibrary.model.Book;
import com.example.bookslibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    
    private final BookService bookService;

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getList();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getByID(id);
    }

    @PostMapping()
    public String createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return "New book added";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted";
    }

    @PutMapping("/{id}")
    public String updateBook(@RequestBody Book newBook, @PathVariable Long id) {
        bookService.updateBook(newBook, id);
        return "Book updated";
    }
}
