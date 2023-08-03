package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.service.LibrarySystemService;
import org.springframework.beans.factory.annotation.Autowired;

import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("library/v1/")
public class LibrarySystemController {

    @Autowired
    private final LibrarySystemService librarySystemService;


    public LibrarySystemController(LibrarySystemService librarySystemService) {
        this.librarySystemService = librarySystemService;
    }

    @GetMapping("books_directory/{page_number}/{page_size}")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable int page_number, @PathVariable int page_size) {
        return librarySystemService.getAllBooks(page_number,page_size);
    }

    @PostMapping("inventory/books")
    public Book addBook(@RequestBody Book book){return librarySystemService.addBook(book);}



    @DeleteMapping("book/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        return librarySystemService.deleteBook(bookId);
    }

    @GetMapping("book/{bookId}")
    public Book findByBookId(@PathVariable int bookId) {
       return librarySystemService.findByBookId(bookId);
    }


    @GetMapping("book/category/{categoryName}")
    public List<Book> findBookByCategoryName(@PathVariable String categoryName){
        return librarySystemService.findBookByCategoryName(categoryName);
    }

    @GetMapping("books/{bookName}")
    public ResponseEntity<List<Book>> findByBookName(@PathVariable String bookName) {
        return librarySystemService.findByBookName(bookName);
    }

    @PutMapping("inventory/book/update/{id}")
    public Book updateBookDetails(@PathVariable("id") int id, @RequestBody Book book ){
        return librarySystemService.updateBookDetails(id,book);
    }

    @GetMapping("inventory/book/{bookId}")
    public Inventory getBookById(@PathVariable int bookId){

        return librarySystemService.getBookById(bookId);
    }

    @PostMapping("inventory")
    public Inventory addInventory(@RequestBody Inventory inventory){
        return librarySystemService.addInventory(inventory);
    }




}