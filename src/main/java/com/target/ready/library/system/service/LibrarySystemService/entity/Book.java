package com.target.ready.library.system.service.LibrarySystemService.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="Book",uniqueConstraints = @UniqueConstraint(columnNames = { "book_name", "author_name" }))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    @JsonProperty("book_id")
    @JsonAlias("bookId")
    private int bookId;

    @Column(name="book_name")
    @JsonProperty("book_name")
    @JsonAlias("bookName")
    private String bookName;

    @Column(name="book_description")
    @JsonProperty("book_description")
    @JsonAlias("bookDescription")
    private String bookDescription;

    @Column(name="author_name")
    @JsonProperty("author_name")
    @JsonAlias("authorName")
    private String authorName;

    @Column(name="publication_year")
    @JsonProperty("publication_year")
    @JsonAlias("publicationYear")
    private int publicationYear;


}
