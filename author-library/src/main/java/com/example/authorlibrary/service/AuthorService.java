package com.example.authorlibrary.service;

import com.example.authorlibrary.manager.AuthorManager;
import com.example.authorlibrary.model.Author;
import com.example.authorlibrary.model.AuthorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor public class AuthorService {
    
    private final AuthorManager authorManager;
    
    public List<Author> getAllAuthors() {
        return authorManager.getAllAuthors().stream().map(this::mapToAuthor).collect(Collectors.toList());
    }
    
    public Author getAuthorById(Long id) {
        return mapToAuthor(authorManager.getAuthorById(id));
    }
    
    public void saveAuthor(Author author) {
        authorManager.saveAuthor(mapToAuthorEntity(author));
    }
    
    public void deleteAuthor(Long id) {
        authorManager.deleteAuthor(id);
    }
    
    public void updateAuthor(Author author, Long id) {
        authorManager.updateAuthor(mapToAuthorEntity(author), id);
    }
    
    private AuthorEntity mapToAuthorEntity(Author source) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(source.getName());
        return authorEntity;
    }
    
    private Author mapToAuthor(AuthorEntity source) {
        Author author = new Author();
        author.setId(source.getId());
        author.setName(source.getName());
        return author;
    }
    
}
