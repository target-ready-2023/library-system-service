package com.target.ready.library.system.service.LibrarySystemService.service;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibrarySystemService {

    private final BookRepository bookRepository;
    private final InventoryRepository inventoryRepository;
    private final BookCategoryRepository bookCategoryRepository;

    public LibrarySystemService(BookRepository bookRepository, InventoryRepository inventoryRepository, BookCategoryRepository bookCategoryRepository){
        this.bookRepository = bookRepository;
        this.inventoryRepository = inventoryRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public ResponseEntity<List<Book>> getAllBooks(int page_number, int page_size) {
        Pageable pageable = PageRequest.of(page_number,page_size);
        Page<Book> findBooks = bookRepository.findAll(pageable);
        List<Book> books =  findBooks.toList();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Book> addBook(Book book){
        return new ResponseEntity<>(bookRepository.save(book),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
        String message = "Book Deleted Successfully";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    public Book findByBookId(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public ResponseEntity<List<Book>> findBookByCategoryName(String categoryName){
        List<BookCategory> bookCategory;
        List<Book> bookDetails = new ArrayList<>();
        bookCategory = bookCategoryRepository.findByCategoryName(categoryName);
        for(BookCategory bookCategory1 : bookCategory){
            int b1 = bookCategory1.getBookId();
            Book book = findByBookId(b1);
            bookDetails.add(book);
        }
        return new ResponseEntity<>(bookDetails,HttpStatus.OK);
    }

   public ResponseEntity<List<Book>> findByBookName(String bookName) {
       List<Book> books= bookRepository.findByBookName(bookName);
       return new ResponseEntity<>(books, HttpStatus.OK);
   }

   //public List<Book> findByBookName(String bookName) {
   //    List<Book> books= bookRepository.findByBookName(bookName);
   //    return books;
   //}

    public ResponseEntity<Book> updateBookDetails(int id, Book book ){
        Book previousBook = bookRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Book with bookID: "+ id + " not found in database"));
        previousBook.setBookName(book.getBookName());
        previousBook.setBookDescription(book.getBookDescription());
        previousBook.setAuthorName(book.getAuthorName());
        previousBook.setPublicationYear(book.getPublicationYear());
        return new ResponseEntity<>(bookRepository.save(previousBook),HttpStatus.OK);
    }

    public ResponseEntity<Inventory> getBookById(int bookId){
        Inventory inventory = inventoryRepository.findById(bookId).orElse(null);
        return new ResponseEntity<>(inventory,HttpStatus.OK);
    }

    public ResponseEntity<Inventory> addInventory(Inventory inventory){

        return new ResponseEntity<>(inventoryRepository.save(inventory),HttpStatus.OK);
    }
}
