package com.target.ready.library.system.service.LibrarySystemService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="author")
public class Author {
    @Id
    private int authorId;
    private String authorName;
    private int bookId;
}
