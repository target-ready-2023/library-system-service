package com.target.ready.library.system.service.LibrarySystemService.service;


import com.target.ready.library.system.service.LibrarySystemService.controller.LibraryControllerTest;
import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;

@SpringBootTest(classes = {CategoryServiceTest.class})
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    BookCategoryRepository bookCategoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void deleteBookCategoryTest()
    {
        List<BookCategory> categories = new ArrayList<>();
        BookCategory category1= new BookCategory();
        category1.setId(3);
        category1.setBookId(1);
        category1.setCategoryName("Thriller");
        categories.add(category1);

        BookCategory category2= new BookCategory();
        category2.setId(4);
        category2.setBookId(2);
        category2.setCategoryName("Suspense");
        categories.add(category2);

        doAnswer((i) -> {
            categories.removeIf(category -> category.getBookId() == 1);
            return null;
        }).when(bookCategoryRepository).deleteById(1);
        categoryService.deleteBookCategory(1);

        assertEquals(categories.size(),1);

    }
}
