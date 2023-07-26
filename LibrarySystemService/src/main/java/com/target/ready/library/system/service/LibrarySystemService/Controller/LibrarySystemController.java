package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/v1/")
public class LibrarySystemController {
    @Autowired
    private final LibraryService libraryService;

    public LibrarySystemController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("books_directory/{pageNumber}/{pageSize}")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable int pageNumber, @PathVariable int pageSize) {
        List<Book> books=libraryService.getAllBooks(pageNumber, pageSize);
        return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
    }

    @PostMapping("inventory/books")

    public Book addBook(@RequestBody Book book){return libraryService.addBook(book);}



    @DeleteMapping("book/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        return libraryService.deleteBook(bookId);
    }

    @GetMapping("book/{bookId}")
    public Book findByBookId(@PathVariable int bookId) {
        return libraryService.findByBookId(bookId);
    }


    @GetMapping("book/category/{categoryName}")
    public List<Book> findBookByCategoryName(@PathVariable String categoryName){
        return libraryService.findBooksByCategoryName(categoryName);
    }

    @GetMapping("books/{bookName}")
    public ResponseEntity<List<Book>> findByBookName(@PathVariable String bookName) {
        List<Book> book = libraryService.findByBookName(bookName);
        return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
    }

    @PutMapping("inventory/book_update/{id}")
    public ResponseEntity<Book> updateBookDetails(@PathVariable("id") int id, @RequestBody Book book ){
        Book updatedBook = libraryService.updateBookDetails(id, book);
        return ResponseEntity.ok(updatedBook);
    }

}