package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/v2")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

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
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }


}
