package com.target.ready.library.system.service.LibrarySystemService.service;

import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceAlreadyExistsException;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.CategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibrarySystemService {

    private final BookRepository bookRepository;
    private final InventoryRepository inventoryRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    public LibrarySystemService(BookRepository bookRepository, InventoryRepository inventoryRepository, BookCategoryRepository bookCategoryRepository, CategoryRepository categoryRepository){
        this.bookRepository = bookRepository;
        this.inventoryRepository = inventoryRepository;
        this.bookCategoryRepository = bookCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks(int page_number, int page_size) {
        Pageable pageable = PageRequest.of(page_number,page_size);
        Page<Book> findBooks = bookRepository.findAll(pageable);
        if(findBooks.isEmpty()){
            throw new ResourceNotFoundException("Currently no books available");
        }
        List<Book> books =  findBooks.toList();
        return books;
    }

    public long getTotalBookCount() {
        return bookRepository.count();
    }

    public Book addBook(Book book){
        try {
            Book book1 = bookRepository.save(book);
            return book1;
        }
        catch (DataIntegrityViolationException ex){
            throw new ResourceAlreadyExistsException("Book Already Exists with same name and author name");
        }

    }


    public String deleteBook(int bookId) {
           bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book doesn't exist"));
            bookRepository.deleteById(bookId);
            return "Book Deleted Successfully";


    }

    public Book findByBookId(int bookId) {
        return bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book doesn't exists"));
    }

    public List<Book> findBookByCategoryName(String categoryName){
        List<BookCategory> bookCategory;
        List<Book> bookDetails = new ArrayList<>();
        bookCategory = bookCategoryRepository.findByCategoryName(categoryName.toLowerCase());
        if(bookCategory.isEmpty()){
            throw new ResourceNotFoundException("No books are available for the given category!");
        }
        for(BookCategory bookCategory1 : bookCategory){
            int b1 = bookCategory1.getBookId();
            Book book = findByBookId(b1);
            bookDetails.add(book);
        }
        return bookDetails;
    }

    public long getTotalBookCategoryCount(String categoryName) {
        //categoryService.findByCategoryName(categoryName);
        long noOfBooks=bookCategoryRepository.countBooksByCategoryName(categoryName.toLowerCase());
        if(noOfBooks==0){
            throw new ResourceNotFoundException("Currently no books available of this category!");
        }
        return noOfBooks;
    }

    public List<Book> findBookByCategoryName(String categoryName , int pageNumber,int pageSize){
        Page<BookCategory> bookCategory;
        List<Book> bookDetails = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        bookCategory = bookCategoryRepository.findByCategoryName(categoryName.toLowerCase(), pageable);
        if (bookCategory.isEmpty())
            throw new ResourceNotFoundException("No books are available for the given category!");
        List<BookCategory> books = bookCategory.toList();
        for(BookCategory bookCategory1 : books){
            int b1 = bookCategory1.getBookId();
            Book book = findByBookId(b1);
            bookDetails.add(book);
        }
        return bookDetails;
    }


    public List<Book> findByBookName(String bookName) {
        List<Book> books= bookRepository.findByBookName(bookName);
        if(books.isEmpty()){
            throw new ResourceNotFoundException("The book with this name doesn't exist");
        }
        return books;
    }

    public Book updateBookDetails(int id, Book book ) {
        Book previousBook = bookRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Book with bookID: "+ id + " not found in database"));
        previousBook.setBookName(book.getBookName());
        previousBook.setBookDescription(book.getBookDescription());
        previousBook.setAuthorName(book.getAuthorName());
        previousBook.setPublicationYear(book.getPublicationYear());
        try{
         return bookRepository.save(previousBook);}
        catch (DataIntegrityViolationException ex){
            throw new ResourceAlreadyExistsException("Book with same name and same author name already exists!");
        }
    }

    public Inventory getBookById(int bookId){

        return inventoryRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book doesn't exists"));

    }

    public Inventory addInventory(Inventory inventory) {
        try {
            return inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException("Book already Exists");
        }
    }
}