package com.example.genrelibrary.genrelibrary.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
    private Long id;
    private String title;
    
    public Genre(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
