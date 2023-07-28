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
@Table(name="Category",uniqueConstraints = @UniqueConstraint(columnNames = { "category_name" }))
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    @JsonProperty("category_id")
    @JsonAlias("categoryId")
    private int categoryId;
    @Column(name="category_name")
    @JsonProperty("category_name")
    @JsonAlias("categoryName")
    private String categoryName;



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
