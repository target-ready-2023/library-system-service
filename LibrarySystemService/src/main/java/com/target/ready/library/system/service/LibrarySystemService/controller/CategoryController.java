package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/v2")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("inventory/category")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/category/{categoryName}")
    public Category findByCategoryName(@PathVariable String categoryName){
        return categoryService.findByCategoryName(categoryName);
    }

    @GetMapping("/category/book/{bookId}")
    public BookCategory findByBookId(@PathVariable int bookId){
        return categoryService.findByBookId(bookId);
    }

    @PostMapping("inventory/book/category")
    public BookCategory addBookCategory(@RequestBody BookCategory bookCategory){
        return categoryService.addBookCategory(bookCategory);
    }

    @DeleteMapping("inventory/book/category/{id}")
    public String deleteBookCategory(@PathVariable int id){
        return categoryService.deleteBookCategory(id);
    }

    @Transactional
    @DeleteMapping("inventory/delete/bookCategory/{id}")
    public String deleteCategories(@PathVariable int id){
        return categoryService.deleteCategories(id);
    }
    @GetMapping("/categories/{page_number}/{page_size}")
    public ResponseEntity<List<Category>> findAllCategories(@PathVariable int page_number, @PathVariable int page_size){
        return categoryService.findAllCategories(page_number, page_size);
    }
}
