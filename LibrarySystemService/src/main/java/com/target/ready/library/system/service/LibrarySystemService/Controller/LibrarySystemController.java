package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/v1")
public class LibrarySystemController {
    @Autowired
    private final LibraryService libraryService;
    public LibrarySystemController(LibraryService libraryService){
        this.libraryService=libraryService;
    }

    @GetMapping("books_directory/{pageNumber}/{pageSize}")
    public List<Book> getAllBooks(@PathVariable int pageNumber,@PathVariable int pageSize) {
        return libraryService.getAllBooks(pageNumber, pageSize);

    }

    @PostMapping("inventory/books")
    public int addBook(@RequestBody Book book){return libraryService.addBook(book);}

    @DeleteMapping("book/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
       return libraryService.deleteBook(bookId);
    }

    @GetMapping("book/{bookId}")
    public Book findByBookId(@PathVariable int bookId){
        return libraryService.findByBookId(bookId);
    }

//    @GetMapping("book/category/{categoryName}")
//    public List<Book> findBookByCategoryName(@PathVariable String categoryName){
//        return libraryService.findBookByCategoryName(categoryName);
//    }
    @PutMapping("inventory/book_update/{id}")
    public ResponseEntity<Book> updateBookDetails(@PathVariable("id") int id, @RequestBody Book book ){
        Book updatedBook = libraryService.updateBookDetails(id, book);
        return ResponseEntity.ok(updatedBook);
    }

}