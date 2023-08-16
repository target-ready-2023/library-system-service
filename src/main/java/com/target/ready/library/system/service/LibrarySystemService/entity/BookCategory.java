package com.target.ready.library.system.service.LibrarySystemService.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Book_Category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonProperty("id")
    @JsonAlias("id")
    private int id;

    @Column(name="book_id")
    @JsonProperty("book_id")
    @JsonAlias("bookId")
    private int bookId;

    @Column(name="category_name")
    @JsonProperty("category_name")
    @JsonAlias("categoryName")
    private String categoryName;

    public BookCategory(int id, int bookId) {
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName.toLowerCase();
    }
}
