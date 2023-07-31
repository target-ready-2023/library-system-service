package com.target.ready.library.system.service.LibrarySystemService.Entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name="user_id")
    @JsonProperty("user_id")
    @JsonAlias("userId")
    private int userId;

    @Column(name="book_id")
    @JsonProperty("book_id")
    @JsonAlias("bookId")
    private int bookId;

    @Column(name="quantity")
    @JsonProperty("quantity")
    @JsonAlias("quantity")
    private int quantity;
}
