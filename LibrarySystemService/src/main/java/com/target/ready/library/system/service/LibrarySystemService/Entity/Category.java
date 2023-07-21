package com.target.ready.library.system.service.LibrarySystemService.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Category",uniqueConstraints = @UniqueConstraint(columnNames = { "category_name" }))
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column(name="category_name")
    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
