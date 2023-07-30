package com.target.ready.library.system.service.LibrarySystemService.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    @JsonProperty("author_id")
    @JsonAlias("authorId")
    private int authorId;

    @Column(name="author_name")
    @JsonProperty("author_name")
    @JsonAlias("authorName")
    private String authorName;

}
