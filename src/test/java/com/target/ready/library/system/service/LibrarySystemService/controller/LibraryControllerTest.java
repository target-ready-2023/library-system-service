package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
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
        Book response = librarySystemController.findByBookId(1);
        assertEquals(1, response.getBookId());
    }

    @Test
    public void getBookByIdTest(){
        Inventory inventory = new Inventory();
        inventory.setInvBookId(1);
        inventory.setNoOfBooksLeft(2);
        inventory.setNoOfCopies(5);

        when(librarySystemService.getBookById(1)).thenReturn(inventory);
        Inventory response = librarySystemController.getBookById(1);
        assertEquals(1, response.getInvBookId());
    }

    @Test
    public void addInventoryTest(){
        Inventory inventory1 = new Inventory();
        inventory1.setInvBookId(1);
        inventory1.setNoOfBooksLeft(2);
        inventory1.setNoOfCopies(5);

        when(librarySystemService.addInventory(inventory1)).thenReturn(inventory1);

        Inventory response = librarySystemController.addInventory(inventory1);
        assertEquals(1, inventory1.getInvBookId());
    }


}
