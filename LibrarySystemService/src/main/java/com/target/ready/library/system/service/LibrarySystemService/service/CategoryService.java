package com.target.ready.library.system.service.LibrarySystemService.service;

import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BookCategoryRepository bookCategoryRepository){
        this.categoryRepository = categoryRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public ResponseEntity<Category> addCategory(Category category){

        return new ResponseEntity<>(categoryRepository.save(category),HttpStatus.OK);
    }

    public ResponseEntity<Category> findByCategoryName(@PathVariable String categoryName){
        Category category = categoryRepository.findByCategoryName(categoryName);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    public ResponseEntity<BookCategory> findByBookId(@PathVariable int bookId){
        BookCategory bookCategory = bookCategoryRepository.findByBookId(bookId);
        return  new ResponseEntity<>(bookCategory,HttpStatus.OK);
    }

    public ResponseEntity<BookCategory> addBookCategory(@RequestBody BookCategory bookCategory){
        return new ResponseEntity<>(bookCategoryRepository.save(bookCategory),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteBookCategory(@PathVariable int id){
        bookCategoryRepository.deleteById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCategories(@PathVariable int id){
        List<BookCategory> bookCategories = bookCategoryRepository.findAllCategoriesByBookId(id);
        for (BookCategory bookCategory:bookCategories
        ) {
            categoryRepository.deleteCategoryByCategoryName(bookCategory.getCategoryName());
        }

        bookCategoryRepository.deleteBookCategoriesByBookId(id);
        return new ResponseEntity<>("Book category deleted",HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> findAllCategories(int page_number, int page_size){
        Pageable pageable = PageRequest.of(page_number,page_size);
        Page<Category> allCategories = categoryRepository.findAll(pageable);
        List<Category> categories = allCategories.toList();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
