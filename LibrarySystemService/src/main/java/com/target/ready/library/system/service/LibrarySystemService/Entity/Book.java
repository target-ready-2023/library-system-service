package com.target.ready.library.system.service.LibrarySystemService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Book")
public class Book {
    @Id
    private int bookId;
    private String bookName;
    private String bookDesc;
    private int publicationYear;
}
