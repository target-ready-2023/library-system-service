package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LibraryServiceTest.class})
public class LibraryServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    BookCategoryRepository bookCategoryRepository;

    @InjectMocks
    LibraryService libraryService;

    @Test
    public void addBookTest(){
        Book book=new Book();
        book.setBookName("Alchemist");
        book.setBookDescription("Follow Your Dreams");
        book.setAuthorName("Paulopoelo");
        book.setPublicationYear(1999);
        when(bookRepository.save(book)).thenAnswer(invocation -> {
           Book book1=invocation.getArgument(0);
           book1.setBookId(1);
           return book1;
        });
        assertEquals(1,libraryService.addBook(book).getBookId());
        assertEquals("Alchemist",libraryService.addBook(book).getBookName());
        assertEquals("Follow Your Dreams",libraryService.addBook(book).getBookDescription());
        assertEquals(1999,libraryService.addBook(book).getPublicationYear());
        assertEquals("Paulopoelo",libraryService.addBook(book).getAuthorName());
    }
}
