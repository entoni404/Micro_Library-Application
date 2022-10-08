package com.example.bookslibrary.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Long id;
    private Author author;
    private String title;
    private int pagesNum;
    private double price;
    private String language;
    private String description;
    private Genre genre;
}
