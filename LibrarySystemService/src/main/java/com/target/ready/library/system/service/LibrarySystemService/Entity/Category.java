package com.target.ready.library.system.service.LibrarySystemService.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
