package com.target.ready.library.system.service.LibrarySystemService.controller;


import com.target.ready.library.system.service.LibrarySystemService.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CategoryControllerTest.class})
public class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    @Test
    public void getAllCategoryTest(){

    }
}
