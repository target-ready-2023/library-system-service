package com.target.ready.library.system.service.LibrarySystemService.controller;


import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void deleteBookCategoryTest()
    {
        BookCategory category= new BookCategory();
        category.setId(1);
        category.setBookId(2);
        category.setCategoryName("Thriller");

        when(categoryService.deleteBookCategory(2)).thenReturn("Deleted");
        String response=categoryController.deleteBookCategory(2);
        assertEquals(response,"Deleted");

    }
}
