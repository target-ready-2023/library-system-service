package com.target.ready.library.system.service.LibrarySystemService.Service;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.Entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.Exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.Repository.AuthorRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
// Note: importing pageable from java.awt.print causes problem because both page and pageable shd be imported from same library
import java.util.List;

@Service
public class LibraryService {
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
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

    public int addBook(Book book){
        Book book1= bookRepository.save(book);
        return book1.getBookId();
    }

    public String deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
        return "Book Deleted Successfully";
    }

    public Book findByBookId(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }



    public Book updateBookDetails(int id, Book book){
        Book previousBook = bookRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Book with bookID: "+ id + " not found in database"));
        previousBook.setBookName(book.getBookName());
        previousBook.setBookDescription(book.getBookDescription());
        previousBook.setAuthorName(book.getAuthorName());
        previousBook.setPublicationYear(book.getPublicationYear());
        return bookRepository.save(previousBook);
    }
}