package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Service.LibraryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LibraryControllerTest.class})
public class LibraryControllerTest {

    @Mock
    LibraryService libraryService;

    @InjectMocks
    LibrarySystemController librarySystemController;

    @Test
    public void addBookTest(){
        Book book=new Book();
        book.setBookName("Alchemist");
        book.setBookDescription("Follow Your Dreams");
        book.setAuthorName("Paulopoelo");
        book.setPublicationYear(1999);
        when(libraryService.addBook(book)).thenReturn(book);
        assertNotNull(librarySystemController.addBook(book));
        assertEquals("Alchemist",librarySystemController.addBook(book).getBookName());
        assertEquals("Follow Your Dreams",librarySystemController.addBook(book).getBookDescription());
        assertEquals("Paulopoelo",librarySystemController.addBook(book).getAuthorName());
        assertEquals(1999,librarySystemController.addBook(book).getPublicationYear());


    }


}
