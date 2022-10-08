package com.example.bookslibrary.service;

import com.example.bookslibrary.model.Author;
import com.example.bookslibrary.model.Genre;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestService {
    
    private static final String GENRE_URL = "http://localhost:8082/genre";
    private static final String AUTHOR_URL = "http://localhost:8083/author";
    RestTemplate restTemplate = new RestTemplate();
    
    public Author getAuthor(Long id) {
        return restTemplate.getForEntity(AUTHOR_URL + "/" + id, Author.class).getBody();
    }
    
    public Genre getGenre(Long id) {
        return restTemplate.getForEntity(GENRE_URL + "/" + id, Genre.class).getBody();
    }
}
