package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Repository.AuthorRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
// Note: importing pageable from java.awt.print causes problem because both page and pageable shd be imported from same library
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

    public List<Book> getAllBooks(int pageNumber,int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Book> findBooks = bookRepository.findAll( pageable);
        List<Book> allBooks = findBooks.getContent();
        return findBooks.toList();
    }

    public String addBook(Book book){
        bookRepository.save(book);
        return "Book Added Successfully";
    }

    public void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book findByBookId(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> findBookByCategoryName(String categoryName) {
        return bookRepository.findByCategoryName(categoryName);
    }
}