package com.example.authorlibrary.controller;

import com.example.authorlibrary.model.Author;
import com.example.authorlibrary.service.AuthorService;
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

@CrossOrigin @RestController @RequestMapping("/author") public class AuthorController {
    
    private AuthorService authorService;
    
    @GetMapping() public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
    
    @GetMapping("/{id}") public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }
    
    @PostMapping() public String saveAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
        return "Author Added";
    }
    
    @DeleteMapping("/{id}") public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "Author Deleted";
    }
    
    @PutMapping("/{id}") public String updateAuthor(@RequestBody Author author, @PathVariable Long id) {
        authorService.updateAuthor(author, id);
        return "Author Updated";
    }
}



