package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Repository.AuthorRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    public LibraryService(BookRepository bookRepository,CategoryRepository categoryRepository){
        this.bookRepository=bookRepository;
        this.categoryRepository=categoryRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}