package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library_service_api/v1")
public class LibrarySystemController {
    @Autowired
    private final LibraryService libraryService;
    public LibrarySystemController(LibraryService libraryService){
        this.libraryService=libraryService;
    }

    @GetMapping("allBooks")
    public List<Book> getAllBooks(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber) {
        int pageSize = 5;
        List<Book> books;

        try {

            if (pageNumber < 0) {
                return null;
            }
            books = libraryService.getAllBooks(pageNumber, pageSize);
            return books;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("inventory/books")
    public String addBook(@RequestBody Book book){return libraryService.addBook(book);}

    @GetMapping("book/{bookId}")
    public Book findByBookId(@PathVariable int bookId){
        return libraryService.findByBookId(bookId);
    }

    @GetMapping("book/category/{categoryName}")
    public List<Book> findBookByCategoryName(@PathVariable String categoryName){
        return libraryService.findBookByCategoryName(categoryName);
    }

}