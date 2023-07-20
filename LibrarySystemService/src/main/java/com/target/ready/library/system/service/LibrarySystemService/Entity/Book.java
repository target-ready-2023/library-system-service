package com.target.ready.library.system.service.LibrarySystemService.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String bookDescription;
    private String categoryName;
    private String authorName;
    private int publicationYear;

}
