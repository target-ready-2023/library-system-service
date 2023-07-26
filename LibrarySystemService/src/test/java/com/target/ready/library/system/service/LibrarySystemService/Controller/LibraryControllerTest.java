package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.CategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.Service.LibraryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LibraryControllerTest.class})
public class LibraryControllerTest {

    @Mock
    LibraryService libraryService;

    @InjectMocks
    LibrarySystemController librarySystemController;

    @Mock
    BookRepository bookRepository;

    @Test
    public void findByBookIdTest(){
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("The Shining");
        book.setBookDescription("Stephen King");
        book.setAuthorName("Chetan");
        book.setPublicationYear(2023);
        when(libraryService.findByBookId(1)).thenReturn(book);
        assert(book.getBookId()==1);
    }

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

   // Book RECORD_1=new Book(1,"A Girl in room 205","An unlove story","Chetan Bhagat",2000);
   // Book RECORD_2=new Book(2,"The Silent Patient","The dangers of unresolved or improperly treated mental illness","Alex Michaelides",	2019);
   // Book RECORD_3=new Book(3,"The Secret Adversary","About the lovable married couple","Agatha Christie",1922);

    @Test
   public void findByBookNameTest(){
       List<Book> books = new ArrayList<>();
       Book book1=new Book(1,
               "The Hound of Death",
               "A young Englishman visiting Cornwall finds himself delving into the legend of a Belgian nun who is living as a refugee in the village."
               ,"Agatha Christie",1933);
       books.add(book1);
       Book book2=new Book(2,
               "The Adventure of Dancing Men",
               "The little dancing men are at the heart of a mystery which seems to be driving his young wife Elsie Patrick to distraction."
               ,"Sir Arthur Conan Doyle",1903);
       books.add(book2);
       when(libraryService.findByBookName("The Hound of Death")).thenReturn(books);
       ResponseEntity<List<Book>> response = librarySystemController.findByBookName(book1.getBookName());
       assertEquals(HttpStatus.OK, response.getStatusCode());
       assertEquals(books, response.getBody());
   }


   // @Test
   // public void getAllBooksTest(){
   //    List<Book> books = new ArrayList<>();
   //    Book book1=new Book(1,
   //            "The Hound of Death",
   //            "A young Englishman visiting Cornwall finds himself delving into the legend of a Belgian nun who is living as a refugee in the village."
   //            ,"Agatha Christie",1933);
   //    books.add(book1);
   //    Book book2=new Book(2,
   //            "The Adventure of Dancing Men",
   //            "The little dancing men are at the heart of a mystery which seems to be driving his young wife Elsie Patrick to distraction."
   //            ,"Sir Arthur Conan Doyle",1903);
   //    books.add(book2);
//
   //     //List<Book> books=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
   //     when(libraryService.getAllBooks(0,5)).thenReturn(books);
   //     ResponseEntity<List<Book>> response = librarySystemController.getAllBooks(0,5);
   //     assertEquals(HttpStatus.OK, response.getStatusCode());
   //     assertEquals(2, response.getBody().size());
   // }
   public void deleteBookTest() {
       Book book = new Book();
       book.setBookId(2);
       book.setBookName("Life of Suraj");
       book.setBookDescription("Masterpiece");
       book.setAuthorName("Suraj");
       book.setPublicationYear(2024);
       when(libraryService.deleteBook(2)).thenReturn("Book Deleted Successfully");
       String response = librarySystemController.deleteBook(book.getBookId());
       assertEquals(response,"Book Deleted Successfully");
   }
}
