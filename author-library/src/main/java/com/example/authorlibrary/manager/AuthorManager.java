package com.example.authorlibrary.manager;

import com.example.authorlibrary.model.AuthorEntity;
import com.example.authorlibrary.respository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class AuthorManager {
    
    private final AuthorRepository authorRepository;
    
    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    public AuthorEntity getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("The resource you are looking for does not exit."));
    }
    
    public void saveAuthor(AuthorEntity author) {
        authorRepository.save(author);
    }
    
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    
    public void updateAuthor(AuthorEntity author, Long id) {
        AuthorEntity existingAuthor = getAuthorById(id);
        existingAuthor.setName(author.getName());
        authorRepository.save(existingAuthor);
    }
}
