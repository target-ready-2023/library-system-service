package com.target.ready.library.system.service.LibrarySystemService.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name="inv_book_id")
    @JsonProperty("inv_book_id")
    @JsonAlias("invBookId")
    private int invBookId;

    @Column(name="no_of_copies")
    @JsonProperty("no_of_copies")
    @JsonAlias("noOfCopies")
    private int noOfCopies;

    @Column(name="no_of_books_left")
    @JsonProperty("no_of_books_left")
    @JsonAlias("noOfBooksLeft")
    private int noOfBooksLeft;

}
