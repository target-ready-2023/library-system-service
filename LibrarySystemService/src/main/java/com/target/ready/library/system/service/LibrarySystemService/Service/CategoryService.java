package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public String addCategory(Category category){
        categoryRepository.save(category);
        return "Category Added Successfully";
    }

    public Category findByCategoryName(String categoryName){
        Category category=categoryRepository.findBycategoryName(categoryName);
        return category;
    }
}
