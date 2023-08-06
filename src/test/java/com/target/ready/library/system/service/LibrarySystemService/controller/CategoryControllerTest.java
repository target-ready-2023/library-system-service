package com.target.ready.library.system.service.LibrarySystemService.controller;


import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
