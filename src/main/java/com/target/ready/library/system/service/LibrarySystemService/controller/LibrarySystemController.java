package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceAlreadyExistsException;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.service.LibrarySystemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.Collections;
import java.util.List;

@RestController
@Validated
@RequestMapping("library/v1/")
public class LibrarySystemController {

    @Autowired
    private final LibrarySystemService librarySystemService;


    public LibrarySystemController(LibrarySystemService librarySystemService) {
        this.librarySystemService = librarySystemService;
    }

    @GetMapping("books_directory/{page_number}/{page_size}")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable int page_number, @PathVariable int page_size) throws ResourceNotFoundException{
        return new ResponseEntity<>(librarySystemService.getAllBooks(page_number,page_size),HttpStatus.OK);
    }
    @GetMapping("/books_directory/total_count")
    public ResponseEntity<Long> getTotalBookCount() throws ResourceNotFoundException{

            long totalCount = librarySystemService.getTotalBookCount();
            return new ResponseEntity<>(totalCount, HttpStatus.OK);

    }

    @PostMapping("inventory/books")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book) throws ResourceAlreadyExistsException{
            Book addedBook = librarySystemService.addBook(book);
            return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
    }

    @DeleteMapping("books/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) throws ResourceNotFoundException {

            String deletionResult = librarySystemService.deleteBook(bookId);
            return new ResponseEntity<>(deletionResult, HttpStatus.ACCEPTED);

    }

    @GetMapping("book/{bookId}")
    public ResponseEntity<Book> findByBookId(@PathVariable int bookId) throws ResourceNotFoundException{

        return new ResponseEntity<>(librarySystemService.findByBookId(bookId), HttpStatus.OK);
    }

    @GetMapping("book/category/{categoryName}/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Book>> findBookByCategoryName(@PathVariable String categoryName, @PathVariable int pageNumber, @PathVariable int pageSize) throws ResourceNotFoundException{
        return new ResponseEntity<>(librarySystemService.findBookByCategoryName(categoryName,pageNumber,pageSize), HttpStatus.OK);
    }

    @GetMapping("/books/category/total_count/{categoryName}")
    public ResponseEntity<Long> getTotalBookCategoryCount(@PathVariable String categoryName) throws ResourceNotFoundException{

            long totalCount = librarySystemService.getTotalBookCategoryCount(categoryName);
            return new ResponseEntity<>(totalCount, HttpStatus.OK);

    }

    @GetMapping("books/{bookName}")
    public ResponseEntity<List<Book>> findByBookName(@PathVariable String bookName) throws ResourceNotFoundException{

            return new ResponseEntity<>(librarySystemService.findByBookName(bookName), HttpStatus.OK);

    }

    @PutMapping("inventory/book/update/{id}")
    public ResponseEntity<?> updateBookDetails(@PathVariable("id") int id, @RequestBody @Valid Book book) throws ResourceAlreadyExistsException{

            return new ResponseEntity<>(librarySystemService.updateBookDetails(id, book), HttpStatus.OK);

    }

    @GetMapping("inventory/book/{bookId}")
    public ResponseEntity<Inventory> getBookById(@PathVariable int bookId) throws ResourceNotFoundException{
        return new ResponseEntity<>(librarySystemService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping("inventory")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) throws ResourceAlreadyExistsException{
        return new ResponseEntity<>(librarySystemService.addInventory(inventory), HttpStatus.CREATED);
    }




}