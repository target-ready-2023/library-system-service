package com.target.ready.library.system.service.LibrarySystemService.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_catalog",uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "book_id" }))

public class UserCatalog{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  @JsonProperty("id")
  @JsonAlias("id")
  private int id;

  @Column(name="user_id")
    @JsonProperty("user_id")
    @JsonAlias("userId")
    private int userId;


    @Column(name="book_id")
    @JsonProperty("book_id")
    @JsonAlias("bookId")
    private int bookId;


}
