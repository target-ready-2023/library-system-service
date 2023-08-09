package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.service.LibrarySystemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable int page_number, @PathVariable int page_size) {
        return new ResponseEntity<>(librarySystemService.getAllBooks(page_number,page_size),HttpStatus.OK);
    }

    @PostMapping("inventory/books")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
        try {
            Book addedBook = librarySystemService.addBook(book);
            return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Book already exists with same name and author name", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("book/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        try {
            String deletionResult = librarySystemService.deleteBook(bookId);
            return new ResponseEntity<>(deletionResult, HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("book/{bookId}")
    public ResponseEntity<Book> findByBookId(@PathVariable int bookId) {

        return new ResponseEntity<>(librarySystemService.findByBookId(bookId), HttpStatus.OK);
    }


    @GetMapping("book/category/{categoryName}")
    public ResponseEntity<List<Book>> findBookByCategoryName(@PathVariable String categoryName){
        return new ResponseEntity<>(librarySystemService.findBookByCategoryName(categoryName), HttpStatus.OK);
    }

    @GetMapping("books/{bookName}")
    public ResponseEntity<List<Book>> findByBookName(@PathVariable String bookName) {
        return new ResponseEntity<>(librarySystemService.findByBookName(bookName), HttpStatus.OK);
    }

    @PutMapping("inventory/book/update/{id}")
    public ResponseEntity<Book> updateBookDetails(@PathVariable("id") int id, @RequestBody Book book ){
        return new ResponseEntity<>(librarySystemService.updateBookDetails(id,book), HttpStatus.OK);
    }

    @GetMapping("inventory/book/{bookId}")
    public ResponseEntity<Inventory> getBookById(@PathVariable int bookId){
        return new ResponseEntity<>(librarySystemService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping("inventory")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory){
        return new ResponseEntity<>(librarySystemService.addInventory(inventory), HttpStatus.CREATED);
    }




}