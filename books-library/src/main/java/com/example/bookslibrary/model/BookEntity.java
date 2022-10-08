package com.example.bookslibrary.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long authorId;
    @Column
    private String title;
    @Column
    private int pagesNum;
    @Column
    private double price;
    @Column
    private String language;
    @Column
    private String description;
    @Column
    private Long genreId;
}
