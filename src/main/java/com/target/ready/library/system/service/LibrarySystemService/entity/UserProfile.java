package com.target.ready.library.system.service.LibrarySystemService.entity;


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
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @Column(name="user_id")
    @JsonProperty("user_id")
    @JsonAlias("userId")
    private int userId;

    @Column(name="user_name")
    @JsonProperty("user_name")
    @JsonAlias("userName")
    private String userName;
}
