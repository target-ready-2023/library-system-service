package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.service.LibrarySystemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LibraryControllerTest.class})
public class LibraryControllerTest {
    @Mock
    LibrarySystemService librarySystemService;

    @InjectMocks
    LibrarySystemController librarySystemController;

//    @Test
//    public void testGetAllBooks() throws Exception{
//        List<Book> records = new ArrayList<Book>();
//        records.add(new Book(1,
//                "Five Point someone",
//                "Semi-autobiographical"
//                ,"Chetan Bhagat",2004));
//        records.add(new Book(2,
//                "The Silent Patient",
//                "The dangers of unresolved or improperly treated mental illness","Alex Michaelides",2019)
//        );
//
//        Pageable pageable = PageRequest.of(0,5);
//        Page<Book> page = new PageImpl<>(records, pageable, records.size());
//        when(bookRepository.findAll(pageable)).thenReturn(page);
//        ResponseEntity<List<Book>> response = librarySystemController.getAllBooks(0,5);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(2, response.getBody().size());
//        }
//

    @Test
    public void findByBookNameTest() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book(1,
                "The Hound of Death",
                "A young Englishman visiting Cornwall finds himself delving into the legend of a Belgian nun who is living as a refugee in the village."
                , "Agatha Christie", 1933);
        books.add(book1);
        Book book2 = new Book(2,
                "The Adventure of Dancing Men",
                "The little dancing men are at the heart of a mystery which seems to be driving his young wife Elsie Patrick to distraction."
                , "Sir Arthur Conan Doyle", 1903);
        books.add(book2);
        when(librarySystemService.findByBookName("The Hound of Death")).thenReturn(books);
        ResponseEntity<List<Book>> response = librarySystemController.findByBookName(book1.getBookName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }

    @Test
    public void deleteBookTest() {

        Book book = new Book();
        book.setBookId(2);
        book.setBookName("Day of the Jackal");
        book.setBookDescription("Masterpiece");
        book.setAuthorName("Frederick Forsyth");
        book.setPublicationYear(1981);

        when(librarySystemService.deleteBook(2)).thenReturn("Book Deleted Successfully");

        ResponseEntity<String> response = librarySystemController.deleteBook(book.getBookId());
        assertEquals(response.getStatusCode(),HttpStatus.ACCEPTED);
        assertEquals(response.getBody(),"Book Deleted Successfully");
    }

}
