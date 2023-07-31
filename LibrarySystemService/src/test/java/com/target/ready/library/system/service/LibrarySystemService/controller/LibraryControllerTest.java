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
//
//    }

}
