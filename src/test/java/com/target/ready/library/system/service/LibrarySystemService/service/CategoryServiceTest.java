package com.target.ready.library.system.service.LibrarySystemService.service;


import com.target.ready.library.system.service.LibrarySystemService.controller.LibraryControllerTest;
import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
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
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryServiceTest.class})
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    BookCategoryRepository bookCategoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void findAllCategoriesByBookIdTest(){
        List<BookCategory> bookCategories= new ArrayList<BookCategory>();
        int bookId=5;
        bookCategories.add(new BookCategory(1,bookId,"Horror"));
        bookCategories.add(new BookCategory(2,bookId,"Adventure"));

        when(bookCategoryRepository.findAllCategoriesByBookId(bookId)).thenReturn(bookCategories);
        List<BookCategory> response = categoryService.findAllCategoriesByBookId(bookId);

        assertEquals(2,response.size());
    }

    @Test
    public void deleteCategoriesTest() throws InterruptedException {
        List<BookCategory> bookCategories = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<BookCategory> bookCategories1 = new ArrayList<>();

        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setId(1);
        bookCategory1.setBookId(2);
        bookCategory1.setCategoryName("Thriller");
        bookCategories.add(bookCategory1);

        Category category1 = new Category();
        category1.setCategoryId(1);
        category1.setCategoryName("Thriller");
        categories.add(category1);

        BookCategory bookCategory2= new BookCategory();
        bookCategory2.setId(2);
        bookCategory2.setBookId(3);
        bookCategory2.setCategoryName("Suspense");
        bookCategories.add(bookCategory2);

        Category category2 = new Category();
        category2.setCategoryId(2);
        category2.setCategoryName("Suspense");
        categories.add(category2);

        bookCategories1.add(bookCategory2);

        doAnswer((i) -> {
            bookCategories.removeIf(bookCategory  -> bookCategory.getBookId() == 2);
            return bookCategories;
        }).when(bookCategoryRepository).deleteBookCategoriesByBookId(2);

        when(bookCategoryRepository.findByCategoryName("Suspense")).thenReturn(new ArrayList<>());

        doAnswer((i) -> {
            categories.removeIf(category -> category.getCategoryName() == "Suspense");
            return null;
        }).when(categoryRepository).deleteByCategoryName("Suspense");

        categoryService.deleteCategories(2);

        assertEquals(bookCategories.size(),1);
        assertEquals(categories.size(),1);

    }
    @Test
    public void addCategoryTest(){
        Category category=new Category();
        category.setCategoryName("Fiction");
        when(categoryRepository.save(category)).thenAnswer(invocation -> {
            Category category1=invocation.getArgument(0);
            category1.setCategoryId(1);
            return category1;
        });
        Category savedCategory=categoryService.addCategory(category);
        assertEquals(1,savedCategory.getCategoryId());
        assertEquals("Fiction",savedCategory.getCategoryName());
    }



    @Test
    public void findByCategoryNameTest(){
        Category category=new Category();
        category.setCategoryId(1);
        category.setCategoryName("Fiction");
        when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(category);
        Category savedCategory=categoryService.findByCategoryName(category.getCategoryName());
        assertEquals("Fiction",savedCategory.getCategoryName());
    }

    @Test
    public void addBookCategoryTest(){
        BookCategory bookCategory=new BookCategory();
        bookCategory.setBookId(1);
        bookCategory.setCategoryName("Fiction");
        when(bookCategoryRepository.save(bookCategory)).thenAnswer(invocation -> {
            BookCategory bookCategory1=invocation.getArgument(0);
            bookCategory1.setId(0);
            return bookCategory1;
        });
        BookCategory savedBookCategory=categoryService.addBookCategory(bookCategory);

        assertEquals(bookCategory.getId(),savedBookCategory.getId());
        assertEquals(bookCategory.getCategoryName(),savedBookCategory.getCategoryName());
        assertEquals(bookCategory.getBookId(),savedBookCategory.getBookId());
    }
    @Test
    public void deleteBookCategoryTest(){
        BookCategory bookCategory=new BookCategory();
        bookCategory.setId(1);
        bookCategory.setCategoryName("Fiction");
        bookCategory.setBookId(1);

        BookCategory bookCategory1=new BookCategory();
        bookCategory1.setBookId(4);
        bookCategory1.setCategoryName("Horror");
        bookCategory1.setId(2);

        List<BookCategory> bookCategoryList=new ArrayList<>();
        bookCategoryList.add(bookCategory1);
        bookCategoryList.add(bookCategory);

        doAnswer((i) -> {
            int id=i.getArgument(0);
            bookCategoryList.removeIf(bookCategory3 -> bookCategory3.getId() == id);
            return null;
        }).when(bookCategoryRepository).deleteById(2);

        String response=categoryService.deleteBookCategory(2);
        assertEquals("Deleted",response);
        assertEquals(1,bookCategoryList.size());

    }


}
