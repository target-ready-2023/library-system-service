package com.target.ready.library.system.service.LibrarySystemService.controller;


import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryControllerTest.class})
public class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    @Test
    public void getAllCategoryTest(){

    }

    @Test
    public void findAllCategoriesByBookIdTest(){
        List<BookCategory> bookCategories= new ArrayList<BookCategory>();
        int bookId=5;
        bookCategories.add(new BookCategory(1,bookId,"Horror"));
        bookCategories.add(new BookCategory(2,bookId,"Adventure"));

        when(categoryService.findAllCategoriesByBookId(bookId)).thenReturn(bookCategories);

        ResponseEntity<?> response = categoryController.findAllCategoriesByBookId(bookId);

        assertEquals(HttpStatus.OK,response.getStatusCode());
//        assertEquals(2,response.getBody().size());
    }

    @Test
    public void deleteCategoryTest() throws InterruptedException {
        BookCategory category= new BookCategory();
        category.setId(1);
        category.setBookId(2);
        category.setCategoryName("Thriller");

        when(categoryService.deleteCategories(2)).thenReturn("Book category deleted");

        ResponseEntity<String> response=categoryController.deleteCategories(2);
        assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
        assertEquals(response.getBody(),"Book category deleted");

    }

    @Test
    public void addCategoryTest(){
        Category category=new Category();
        category.setCategoryName("Fiction");
        category.setCategoryId(1);
        when(categoryService.addCategory(category)).thenReturn(category);
        ResponseEntity<?> response=categoryController.addCategory(category);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(category,response.getBody());
    }

    @Test
    public void findByCategoryNameTest(){
        Category category=new Category();
        category.setCategoryId(1);
        category.setCategoryName("Horror");
        when(categoryService.findByCategoryName(category.getCategoryName())).thenReturn(category);
        ResponseEntity<?> response=categoryController.findByCategoryName(category.getCategoryName());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(category,response.getBody());
    }





    @Test
    public void addBookCategoryTest(){
        BookCategory bookCategory=new BookCategory();
        bookCategory.setBookId(14);
        bookCategory.setCategoryName("Horror");
        when(categoryService.addBookCategory(bookCategory)).thenAnswer(invocation -> {
            BookCategory bookCategory1=invocation.getArgument(0);
            bookCategory1.setId(1);
            return bookCategory1;
        });
        ResponseEntity<BookCategory> response=categoryController.addBookCategory(bookCategory);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(bookCategory,response.getBody());
    }



    @Test
    public void deleteBookCategory(){
        BookCategory bookCategory=new BookCategory();
        bookCategory.setId(1);
        bookCategory.setCategoryName("Horror");
        bookCategory.setBookId(14);

       when(categoryService.deleteBookCategory(bookCategory.getId())).thenReturn("Deleted");

        ResponseEntity<String> response=categoryController.deleteBookCategory(2);
        assertEquals("Deleted",response.getBody());
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
    }
}
