package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        books.add(book2);

        doAnswer((invocation) -> {
            int id=invocation.getArgument(0);
            books.removeIf(book->book.getBookId()==id);
            return null;
        }).when(bookRepository).deleteById(1);
        libraryService.deleteBook(1);
        assertEquals(books.size(),1);
    }

    @Test
    public void findAllBooksTest(){
        List<Book> records = new ArrayList<Book>();
        records.add(new Book(1,
                "Five Point someone",
                "Semi-autobiographical"
                ,"Chetan Bhagat",2004));
        records.add(new Book(2,
                "The Silent Patient",
                "The dangers of unresolved or improperly treated mental illness","Alex Michaelides",2019)
        );

        Pageable pageable = PageRequest.of(0,5);
        Page<Book> page = new PageImpl<>(records, pageable, records.size());
        when(bookRepository.findAll(pageable)).thenReturn(page);
        List<Book> response=libraryService.getAllBooks(0,5);
        assertEquals(2, response.size());
    }
}
