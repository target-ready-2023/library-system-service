package com.target.ready.library.system.service.LibrarySystemService.repository;

import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);
    void deleteCategoryByCategoryName(String categoryName);
}
