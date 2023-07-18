package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("library_service_api/v1")
public class LibrarySystemController {
    private final LibraryService libraryService;
    public LibrarySystemController(LibraryService libraryService){
        this.libraryService=libraryService;
    }

    @GetMapping("getAllBooks")
    public List<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }


}
