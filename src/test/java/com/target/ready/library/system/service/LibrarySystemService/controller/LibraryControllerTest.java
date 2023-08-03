package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
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
        ResponseEntity<List<Book>> response = librarySystemController.findBookByCategoryName(bookCategory1.getCategoryName());
        assertEquals(response.getBody(), returnBooks);
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


}
