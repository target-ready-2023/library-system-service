package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceAlreadyExistsException;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.service.LibrarySystemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {LibraryControllerTest.class})
public class LibraryControllerTest {
    @Mock
    LibrarySystemService librarySystemService;

    @InjectMocks
    LibrarySystemController librarySystemController;

    @Test
    public void testGetAllBooks() throws Exception{
        List<Book> records = new ArrayList<Book>();
        records.add(new Book(1,
                "Five Point someone",
                "Semi-autobiographical"
                ,"Chetan Bhagat",2004));
        records.add(new Book(2,
                "The Silent Patient",
                "The dangers of unresolved or improperly treated mental illness","Alex Michaelides",2019)
        );

        when(librarySystemService.getAllBooks(0,5)).thenReturn(records);
        ResponseEntity<List<Book>> response = librarySystemController.getAllBooks(0,5);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(records.size(), response.getBody().size());
    }

    @Test
    public void getTotalBookCountTest() {

        List<Book> records = new ArrayList<>();
        records.add(new Book(1, "Five Point Someone", "Semi-autobiographical", "Chetan Bhagat", 2004));
        records.add(new Book(2, "The Silent Patient", "The dangers of unresolved or improperly treated mental illness", "Alex Michaelides", 2019));
        long successResult = records.size();
        when(librarySystemService.getTotalBookCount()).thenReturn(successResult);
        ResponseEntity<Long> successResponse = librarySystemController.getTotalBookCount();

        assertEquals(HttpStatus.OK, successResponse.getStatusCode());
        assertEquals(successResult, successResponse.getBody());

        // Error scenario
        when(librarySystemService.getTotalBookCount()).thenThrow(new RuntimeException("Some error"));
        ResponseEntity<Long> errorResponse = librarySystemController.getTotalBookCount();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.getStatusCode());
        assertEquals(0L, errorResponse.getBody());
    }

    @Test
    public void addBookTest() throws ResourceAlreadyExistsException {

        Book inputBook = new Book();
        inputBook.setBookName("A Song of Ice and Fire");
        inputBook.setBookDescription("introduces readers to the fictional continents of Westeros and Essos");
        inputBook.setAuthorName("George R.R. Martin");
        inputBook.setPublicationYear(1996);
        Book addedBook = new Book(1, inputBook.getBookName(), inputBook.getBookDescription(),
                inputBook.getAuthorName(), inputBook.getPublicationYear());

        when(librarySystemService.addBook(any(Book.class))).thenReturn(addedBook);

        ResponseEntity<?> response = librarySystemController.addBook(inputBook);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addedBook, response.getBody());
    }

    @Test
    public void findByBookIdTest(){
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("Five Point someone");
        book.setBookDescription("Semi-autobiographical");
        book.setAuthorName("Chetan Bhagat");
        book.setPublicationYear(2004);

        when(librarySystemService.findByBookId(1)).thenReturn(book);
        Book response = librarySystemController.findByBookId(1).getBody();
        assertEquals(1, response.getBookId());
    }

    @Test
    public void getBookByIdTest(){
        Inventory inventory = new Inventory();
        inventory.setInvBookId(1);
        inventory.setNoOfBooksLeft(2);
        inventory.setNoOfCopies(5);

        when(librarySystemService.getBookById(1)).thenReturn(inventory);
        Inventory response = librarySystemController.getBookById(1).getBody();
        assertEquals(1, response.getInvBookId());
    }

    @Test
    public void addInventoryTest() {
        Inventory inventory1 = new Inventory();
        inventory1.setInvBookId(1);
        inventory1.setNoOfBooksLeft(2);
        inventory1.setNoOfCopies(5);

        when(librarySystemService.addInventory(inventory1)).thenReturn(inventory1);

        Inventory response = librarySystemController.addInventory(inventory1).getBody();
        assertEquals(1, inventory1.getInvBookId());
    }
    @Test
    public void findBookByCategoryNameTest() {
        List<Book> books = new ArrayList<>();
        List<BookCategory> bookCategories = new ArrayList<>();
        List<Book> returnBooks = new ArrayList<>();
        Book book1 = new Book(1,
                "Harry Potter and the Philosopher's Stone",
                "Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry."
                , "J. K. Rowling", 1997);
        books.add(book1);
        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setCategoryName("Fiction");
        bookCategory1.setBookId(1);
        bookCategory1.setId(1);
        bookCategories.add(bookCategory1);

        Book book2 = new Book(2,
                "The Immortals of Meluha",
                "follows the story of a man named Shiva, who lives in the Tibetan region – Mount Kailash."
                , "Amish Tripathi", 2010);
        books.add(book2);
        BookCategory bookCategory2 = new BookCategory();
        bookCategory2.setCategoryName("Sci-Fi");
        bookCategory2.setBookId(2);
        bookCategory2.setId(2);
        bookCategories.add(bookCategory2);


        when(librarySystemService.findBookByCategoryName("Sci-Fi")).thenReturn(returnBooks);
        ResponseEntity<List<Book>> response = librarySystemController.findBookByCategoryName(bookCategory1.getCategoryName(),0,5);
        assertEquals(response.getBody(), returnBooks);

    }
    @Test
    public void getTotalBookCategoryCountTest() {
        List<Book> books = new ArrayList<>();
        List<BookCategory> bookCategories = new ArrayList<>();
        List<Book> returnBooks = new ArrayList<>();
        Book book1 = new Book(1,
                "Harry Potter and the Philosopher's Stone",
                "Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry."
                , "J. K. Rowling", 1997);
        books.add(book1);
        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setCategoryName("Fiction");
        bookCategory1.setBookId(1);
        bookCategory1.setId(1);
        bookCategories.add(bookCategory1);

        Book book2 = new Book(2,
                "The Immortals of Meluha",
                "follows the story of a man named Shiva, who lives in the Tibetan region – Mount Kailash."
                , "Amish Tripathi", 2010);
        books.add(book2);
        BookCategory bookCategory2 = new BookCategory();
        bookCategory2.setCategoryName("Sci-Fi");
        bookCategory2.setBookId(2);
        bookCategory2.setId(2);
        bookCategories.add(bookCategory2);

        long totalCount=0;
        when(librarySystemService.getTotalBookCategoryCount("Sci-Fi")).thenReturn(totalCount);
        ResponseEntity<Long> categoryCount=librarySystemController.getTotalBookCategoryCount("Sci-Fi");
        assertEquals(HttpStatus.OK, categoryCount.getStatusCode());
        assertEquals(totalCount,categoryCount.getBody());
    }

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
    @Test
    void updateBookDetailsSuccessfulTest() {
        int bookId = 1;
        Book bookToUpdate = new Book();
        bookToUpdate.setBookName("A leader who had no title");
        bookToUpdate.setBookDescription("A motivational book for entrepreneurs");
        bookToUpdate.setAuthorName("Robin Sharma");
        bookToUpdate.setPublicationYear(2011);

        Book updatedBook = new Book();
        updatedBook.setBookId(bookId);
        updatedBook.setBookName("The 5 am club");
        updatedBook.setBookDescription("A self development book");
        updatedBook.setAuthorName("Robin Sharma");
        updatedBook.setPublicationYear(2013);

        when(librarySystemService.updateBookDetails(eq(bookId), eq(bookToUpdate))).thenReturn(updatedBook);
        ResponseEntity<?> response = librarySystemController.updateBookDetails(bookId, bookToUpdate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
    }
    @Test
    void updateBookDetailsConflictTest() {
        int bookId = 1;
        Book bookToUpdate = new Book();
        bookToUpdate.setBookName("The 5 am club");
        bookToUpdate.setBookDescription("A self development book");
        bookToUpdate.setAuthorName("Robin Sharma");
        bookToUpdate.setPublicationYear(2013);

        when(librarySystemService.updateBookDetails(eq(bookId), eq(bookToUpdate)))
                .thenThrow(new ResourceAlreadyExistsException("A Book already exists with same name and author name"));
        assertThrows(ResourceAlreadyExistsException.class,
                () -> librarySystemController.updateBookDetails(bookId, bookToUpdate));
    }

}
