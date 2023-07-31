package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/v2")
public class CategoryController {


    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final BookCategoryRepository bookCategoryRepository;

    public CategoryController(CategoryRepository categoryRepository, BookCategoryRepository bookCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @PostMapping("inventory/category")
    public Category addCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping("/category/{categoryName}")
    public Category findByCategoryName(@PathVariable String categoryName){
        return categoryRepository.findByCategoryName(categoryName);
    }

    @GetMapping("/category/book/{bookId}")
    public BookCategory findByBookId(@PathVariable int bookId){
        return bookCategoryRepository.findByBookId(bookId);
    }

    @PostMapping("inventory/book/category")
    public BookCategory addBookCategory(@RequestBody BookCategory bookCategory){
        return bookCategoryRepository.save(bookCategory);
    }

    @DeleteMapping("inventory/book/category/{id}")
    public String deleteBookCategory(@PathVariable int id){
        bookCategoryRepository.deleteById(id);
        return "Deleted";
    }

    @Transactional
    @DeleteMapping("inventory/delete/bookCategory/{id}")
    public String deleteCategories(@PathVariable int id){
        List<BookCategory> bookCategories = bookCategoryRepository.findAllCategoriesByBookId(id);
        for (BookCategory bookCategory:bookCategories
        ) {
            categoryRepository.deleteCategoryByCategoryName(bookCategory.getCategoryName());
        }

        bookCategoryRepository.deleteBookCategoriesByBookId(id);
        return "Book category deleted";
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        System.out.println("Got all the categories");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("categories/{bookId}")
    public ResponseEntity<List<BookCategory>> findCategoryByBookId(@PathVariable int bookId) {
        List<BookCategory> categories = bookCategoryRepository.findAllCategoriesByBookId(bookId);

        if (categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categories);
    }

}
