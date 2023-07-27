package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.Exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {LibraryServiceTest.class})
public class LibraryServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    BookCategoryRepository bookCategoryRepository;

    @InjectMocks
    LibraryService libraryService;
    @Test
    public void findByBookIdTest(){
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("The Shining");
        book.setBookDescription("Stephen King");
        book.setAuthorName("Chetan");
        book.setPublicationYear(2023);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        assertEquals(book.getBookId(),libraryService.findByBookId(1).getBookId());
    }
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

        when(bookRepository.findByBookName("The Hound of Death")).thenReturn(books);
        List<Book> result = libraryService.findByBookName(book1.getBookName());
        assertEquals(books, result);

        //when(bookRepository.findByBookName("The Hound")).thenReturn(Collections.emptyList());
        //List<Book> result = libraryService.findByBookName(book1.getBookName());
        //assertTrue(result.isEmpty());
    }

    @Test
    public void deleteBookTest() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setBookId(1);
        book1.setBookName("Life of Suraj 1");
        book1.setBookDescription("Masterpiece");
        book1.setAuthorName("Suraj");
        book1.setPublicationYear(2024);
        books.add(book1);
        Book book2 = new Book();
        book2.setBookId(2);
        book2.setBookName("Life of Suraj 2");
        book2.setBookDescription("Masterpiece");
        book2.setAuthorName("Suraj");
        book2.setPublicationYear(2024);
        books.add(book1);

        doAnswer((i) -> {
            books.remove(0);
            return null;
        }).when(bookRepository).deleteById(1);
        libraryService.deleteBook(1);
        assertEquals(books.size(),1);
    }
    @Test
    public void updateBookFoundTest() {
        int bookId = 1;
        Book bookToUpdate = new Book();
        bookToUpdate.setBookName("The 5 am club");
        bookToUpdate.setBookDescription("A self development book");
        bookToUpdate.setAuthorName("Robin Sharma");
        bookToUpdate.setPublicationYear(2013);

        Book existingBook = new Book();
        existingBook.setBookId(bookId);
        existingBook.setBookName("The monk who sold his ferrari");
        existingBook.setBookDescription("Fictional motivation");
        existingBook.setAuthorName("Robin Sharma");
        existingBook.setPublicationYear(1996);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book argumentBook = invocation.getArgument(0);
            Book newBook = new Book();
            newBook.setBookId(bookId);
            newBook.setBookName(argumentBook.getBookName());
            newBook.setBookDescription(argumentBook.getBookDescription());
            newBook.setAuthorName(argumentBook.getAuthorName());
            newBook.setPublicationYear(argumentBook.getPublicationYear());
            return newBook;
        });

        Book updatedBook = libraryService.updateBookDetails(bookId, bookToUpdate);

        assertEquals(bookId, updatedBook.getBookId());
        assertEquals(bookToUpdate.getBookName(), updatedBook.getBookName());
        assertEquals(bookToUpdate.getBookDescription(), updatedBook.getBookDescription());
        assertEquals(bookToUpdate.getAuthorName(), updatedBook.getAuthorName());
        assertEquals(bookToUpdate.getPublicationYear(), updatedBook.getPublicationYear());

        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void updateBookNotFoundTest() {
        int bookId = 1;
        Book bookToUpdate = new Book();
        bookToUpdate.setBookName("The 5 am club");
        bookToUpdate.setBookDescription("A self development book");
        bookToUpdate.setAuthorName("Robin Sharma");
        bookToUpdate.setPublicationYear(2013);

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            libraryService.updateBookDetails(bookId, bookToUpdate);
        });
        verify(bookRepository, times(1)).findById(eq(bookId));
        verify(bookRepository, never()).save(any(Book.class));
    }
}
