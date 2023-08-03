package com.target.ready.library.system.service.LibrarySystemService.service;


import com.target.ready.library.system.service.LibrarySystemService.controller.LibraryControllerTest;
import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = {LibrarySystemServiceTest.class})
public class LibrarySystemServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    BookCategoryRepository bookCategoryRepository;

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    LibrarySystemService librarySystemService;

    @Test
    public void findByBookIdTest(){
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("The Shining");
        book.setBookDescription("Stephen King");
        book.setAuthorName("Chetan");
        book.setPublicationYear(2023);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Book response=librarySystemService.findByBookId(1);
        assert(response.getBookId()==1);
    }

    @Test
    public void getBookByIdTest(){
        Inventory inventory = new Inventory();
        inventory.setInvBookId(1);
        inventory.setNoOfBooksLeft(2);
        inventory.setNoOfCopies(5);

        when(inventoryRepository.findById(1)).thenReturn(Optional.of(inventory));
        Inventory response = librarySystemService.getBookById(1);
        assert(response.getInvBookId()==1);
    }


    @Test
    public void addInventoryTest(){
        Inventory inventory1 = new Inventory();
        inventory1.setInvBookId(1);
        inventory1.setNoOfBooksLeft(2);
        inventory1.setNoOfCopies(5);

        when(inventoryRepository.save(inventory1)).thenReturn(inventory1);

        Inventory response = librarySystemService.addInventory(inventory1);
        Assertions.assertEquals(1, inventory1.getInvBookId());
    }
}
