package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryServiceTest.class})
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void findAllCategoriesTest(){
        List<Category> myCategories = new ArrayList<Category>();

        myCategories.add(new Category(1,"Horror"));
        myCategories.add(new Category(2, "Adventure"));

        when(categoryRepository.findAll()).thenReturn(myCategories);
        assertEquals(2,categoryService.getAllCategories().size());
    }
}
