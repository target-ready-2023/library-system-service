package com.target.ready.library.system.service.LibrarySystemService.service;

import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceAlreadyExistsException;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookCategoryRepository bookCategoryRepository;

    private final Lock lock = new ReentrantLock();

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BookCategoryRepository bookCategoryRepository){
        this.categoryRepository = categoryRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public Category addCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException("Category already exists");
        }
    }

    public Category findByCategoryName(String categoryName) {
         Category categor1=categoryRepository.findByCategoryName(categoryName);

         if(categor1==null){
             throw new ResourceNotFoundException("Category not found with given category name");
         }
        return categor1;
    }

    public BookCategory findByBookId(int bookId){
        return bookCategoryRepository.findByBookId(bookId);
    }

    public BookCategory addBookCategory(BookCategory bookCategory){

        try {
            return bookCategoryRepository.save(bookCategory);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException("Given book with given category already exists");
        }
    }

    public String deleteBookCategory(int id){
        bookCategoryRepository.deleteById(id);
        return "Deleted";
    }

    @Transactional
    public String deleteCategories(int id) throws InterruptedException {
//        List<BookCategory> bookCategories = bookCategoryRepository.findAllCategoriesByBookId(id);

        lock.lock();
        try{
            List<BookCategory> bookCategories=bookCategoryRepository.deleteBookCategoriesByBookId(id);
            bookCategoryRepository.flush();
            Thread.sleep(3000);
            for (BookCategory bookCategory:bookCategories
            ) {
                String categoryName=bookCategory.getCategoryName();
                List<BookCategory> bookCategories1=bookCategoryRepository.findByCategoryName(categoryName);
                System.out.println("Before"+bookCategories1);
                if(bookCategories1.isEmpty()) {
                    System.out.println("After"+bookCategories1);
                    categoryRepository.deleteByCategoryName(categoryName);
                }

            }
            Thread.sleep(2000);

        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            lock.unlock();
        }





        return "Book category deleted";
    }

    public List<Category> findAllCategories(int page_number, int page_size){
        Pageable pageable = PageRequest.of(page_number,page_size);
        Page<Category> allCategories = categoryRepository.findAll(pageable);
        List<Category> categories = allCategories.toList();
        if(categories.isEmpty()){
            throw new ResourceNotFoundException("No category available currently!");
        }
        return categories;
    }

    public List<BookCategory> findAllCategoriesByBookId(int bookId){
        List<BookCategory> bookCategory=bookCategoryRepository.findAllCategoriesByBookId(bookId);
        if(bookCategory.isEmpty()){
            throw new ResourceNotFoundException("Categories of the given book does not exists");
        }
        return bookCategory;
    }


}
