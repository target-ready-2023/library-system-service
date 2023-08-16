package com.target.ready.library.system.service.LibrarySystemService.service;


import com.target.ready.library.system.service.LibrarySystemService.controller.LibraryControllerTest;
import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import com.target.ready.library.system.service.LibrarySystemService.entity.Category;
import com.target.ready.library.system.service.LibrarySystemService.entity.Inventory;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookCategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.CategoryRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
//import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = {LibrarySystemServiceTest.class})
public class LibrarySystemServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    BookCategoryRepository bookCategoryRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    LibrarySystemService librarySystemService;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void findByBookIdTest(){
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("The Shining");
        book.setBookDescription("Stephen King");
        book.setAuthorName("Chetan");
        book.setPublicationYear(2023);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Book response=librarySystemService.findByBookId(1);
        assert(response.getBookId()==1);
    }
    @Test
    public void getAllBooksTest(){
        List<Book> records = new ArrayList<Book>();
        records.add(new Book(1,
                "Five Point someone",
                "Semi-autobiographical"
                ,"Chetan Bhagat",2004));
        records.add(new Book(2,
                "The Silent Patient",
                "The dangers of unresolved or improperly treated mental illness","Alex Michaelides",2019)
        );

        Pageable pageable = PageRequest.of(0,5);
        Page<Book> page = new PageImpl<>(records, pageable, records.size());
        when(bookRepository.findAll(pageable)).thenReturn(page);
        List<Book> books =librarySystemService.getAllBooks(0,5);
        assertEquals(page.getTotalElements(), books.size());

    }
    @Test
    public void getTotalBookCountTest() {
        List<Book> records = new ArrayList<Book>();
        records.add(new Book(1,
                "Five Point someone",
                "Semi-autobiographical"
                ,"Chetan Bhagat",2004));
        records.add(new Book(2,
                "The Silent Patient",
                "The dangers of unresolved or improperly treated mental illness","Alex Michaelides",2019)
        );
        long repoCount=0;
        when(bookRepository.count()).thenReturn(repoCount);
        long serviceCount=librarySystemService.getTotalBookCount();
        assertEquals(repoCount,serviceCount);
    }
    @Test
    public void getBookByIdTest(){
        Inventory inventory = new Inventory();
        inventory.setInvBookId(1);
        inventory.setNoOfBooksLeft(2);
        inventory.setNoOfCopies(5);

        when(inventoryRepository.findById(1)).thenReturn(Optional.of(inventory));
        Inventory response = librarySystemService.getBookById(1);
        assert(response.getInvBookId()==1);
    }

    @Test
    public void addInventoryTest(){
        Inventory inventory1 = new Inventory();
        inventory1.setInvBookId(1);
        inventory1.setNoOfBooksLeft(2);
        inventory1.setNoOfCopies(5);

        when(inventoryRepository.save(inventory1)).thenReturn(inventory1);

        Inventory response = librarySystemService.addInventory(inventory1);
        Assertions.assertEquals(1, inventory1.getInvBookId());
    }

    //@Test
    //public void findBookByCategoryNameForPagenationTest() {
//
    //    List<BookCategory> bookCategories = new ArrayList<BookCategory>();
//
    //    bookCategories.add(new BookCategory(1,1,"fiction"));
    //    bookCategories.add(new BookCategory(2,2,"sci-fi"));
//
    //    BookCategory bookCategory2 = new BookCategory();
    //    bookCategory2.setCategoryName("sci-fi");
    //    bookCategory2.setBookId(2);
    //    bookCategory2.setId(2);
    //    bookCategories.add(bookCategory2);
//
    //    Pageable pageable = PageRequest.of(0,5);
    //    Page<BookCategory> page = new PageImpl<>(bookCategories, pageable, bookCategories.size());
    //    when(bookCategoryRepository.findByCategoryName("sci-fi",pageable)).thenReturn(page);
    //    List<Book> response = librarySystemService.findBookByCategoryName(bookCategory2.getCategoryName(),0,5);
    //    assertEquals( page.getTotalElements(),response.size());
    //}



        @Test
        public void testFindBookByCategoryNameNoBooks() {
            String categoryName = "fiction";
            int pageNumber = 0;
            int pageSize = 10;

            Pageable pageable = PageRequest.of(pageNumber, pageSize);

            Page<BookCategory> mockPage = new PageImpl<>(new ArrayList<>(), pageable, 0);

            // Mocking the behavior of the bookCategoryRepository's findByCategoryName method to return an empty page
            when(bookCategoryRepository.findByCategoryName(eq(categoryName.toLowerCase()), eq(pageable))).thenReturn(mockPage);

            // Call the method to be tested and expect a ResourceNotFoundException
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                librarySystemService.findBookByCategoryName(categoryName, pageNumber, pageSize);
            });

            // Verify that findByCategoryName was called with the correct categoryName and pageable
            verify(bookCategoryRepository).findByCategoryName(eq(categoryName.toLowerCase()), eq(pageable));

            // Verify that findByBookId was not called since there are no book categories
            verify(bookCategoryRepository, never()).findByBookId(anyInt());
        }

    //@Test
    //public void findBookByCategoryNameTest(){
    //    String categoryName = "fiction";
    //    List<BookCategory> mockBookCategories = new ArrayList<>();
    //    mockBookCategories.add(new BookCategory(1, 1));
    //    mockBookCategories.add(new BookCategory(2, 2));
    //    when(bookCategoryRepository.findByCategoryName(categoryName)).thenReturn(mockBookCategories);
    //    when(librarySystemService.findByBookId(1)).thenReturn(new Book());
    //    when(librarySystemService.findByBookId(2)).thenReturn(new Book());
    //    List<Book> result = librarySystemService.findBookByCategoryName(categoryName);
    //    verify(bookCategoryRepository).findByCategoryName(categoryName);
    //    verify(bookCategoryRepository, times(2)).findByBookId(anyInt());
    //    Assertions.assertEquals(2, result.size());
//
    //}

    @Test
    public void testFindBookByCategoryNameNoBooksWithOutPagenation() {
        String categoryName = "fiction";
        List<BookCategory> mockBookCategories = new ArrayList<>();
        when(bookCategoryRepository.findByCategoryName(categoryName.toLowerCase())).thenReturn(mockBookCategories);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            librarySystemService.findBookByCategoryName(categoryName);
        });

        verify(bookCategoryRepository).findByCategoryName(categoryName.toLowerCase());

        verify(bookCategoryRepository, never()).findByBookId(anyInt());
    }

   //@Test
    public void getTotalBookCategoryCountTest() {
       List<BookCategory> bookCategories = new ArrayList<BookCategory>();
       bookCategories.add(new BookCategory(1,1,"fiction"));
       bookCategories.add(new BookCategory(2,2,"fiction"));
       BookCategory bookCategory2 = new BookCategory();
       bookCategory2.setCategoryName("sci-fi");
       bookCategory2.setBookId(2);
       bookCategory2.setId(2);
       bookCategories.add(bookCategory2);
       long repoCount=0;
       when(bookCategoryRepository.countBooksByCategoryName("sci-fi")).thenReturn(repoCount);
       long serviceCount=librarySystemService.getTotalBookCategoryCount("sci-fi");
       assertEquals(repoCount,serviceCount);

    }


    @Test
    public void findByBookNameTest(){
        List<Book> books = new ArrayList<>();
        Book book1=new Book(1,
                "The Hound of Death",
                "A young Englishman visiting Cornwall finds himself delving into the legend of a Belgian nun who is living as a refugee in the village."
                ,"Agatha Christie",1933);
        books.add(book1);

        Book book2=new Book(2,
                "The Adventure of Dancing Men",
                "The little dancing men are at the heart of a mystery which seems to be driving his young wife Elsie Patrick to distraction."
                ,"Sir Arthur Conan Doyle",1903);
        books.add(book2);

        when(bookRepository.findByBookName("The Hound of Death")).thenReturn(books);
        List<Book> result = librarySystemService.findByBookName(book1.getBookName());
        assertEquals(books,result);

        //when(bookRepository.findByBookName("The Hound")).thenReturn(Collections.emptyList());
        //List<Book> result = libraryService.findByBookName(book1.getBookName());
        //assertTrue(result.isEmpty());
    }

    @Test
    public void deleteBookTest() {
        int bookIdToDelete = 1;
        when(bookRepository.findById(bookIdToDelete)).thenReturn(Optional.of(new Book()));
        String result = librarySystemService.deleteBook(bookIdToDelete);
        verify(bookRepository).findById(bookIdToDelete);
        verify(bookRepository).deleteById(bookIdToDelete);
        Assertions.assertEquals("Book Deleted Successfully", result);
    }

    @Test
    public void addBookTest(){
        Book book=new Book();
        book.setAuthorName("Devdutta Pattanaik");
        book.setBookName("Jaya : An Illustrated Retelling of Mahabharata");
        book.setBookDescription("This presents precisely that fresh perspective on the epic saga of Mahabharata");
        book.setPublicationYear(2023);
        when(bookRepository.save(book)).thenAnswer(invocation -> {
            Book book1=invocation.getArgument(0);
            book1.setBookId(1);
            return book1;
        });
        Book savedBook=librarySystemService.addBook(book);
        assertEquals(book.getBookId(),savedBook.getBookId());
        assertEquals(book,savedBook);
    }
    @Test
    public void updateBookFoundTest() {
        int bookId = 1;
        Book bookToUpdate = new Book();
        bookToUpdate.setBookName("The 5 am club");
        bookToUpdate.setBookDescription("A self development book");
        bookToUpdate.setAuthorName("Robin Sharma");
        bookToUpdate.setPublicationYear(2013);

        Book existingBook = new Book();
        existingBook.setBookId(bookId);
        existingBook.setBookName("The monk who sold his ferrari");
        existingBook.setBookDescription("Fictional motivation");
        existingBook.setAuthorName("Robin Sharma");
        existingBook.setPublicationYear(1996);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book argumentBook = invocation.getArgument(0);
            Book newBook = new Book();
            newBook.setBookId(bookId);
            newBook.setBookName(argumentBook.getBookName());
            newBook.setBookDescription(argumentBook.getBookDescription());
            newBook.setAuthorName(argumentBook.getAuthorName());
            newBook.setPublicationYear(argumentBook.getPublicationYear());
            return newBook;
        });
        Book updatedBook = librarySystemService.updateBookDetails(bookId, bookToUpdate);
        assertEquals(bookId, updatedBook.getBookId());
        assertEquals(bookToUpdate.getBookName(), updatedBook.getBookName());
        assertEquals(bookToUpdate.getBookDescription(), updatedBook.getBookDescription());
        assertEquals(bookToUpdate.getAuthorName(), updatedBook.getAuthorName());
        assertEquals(bookToUpdate.getPublicationYear(), updatedBook.getPublicationYear());
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void updateBookNotFoundTest() {
        int bookId = 1;
        Book bookToUpdate = new Book();
        bookToUpdate.setBookName("The 5 am club");
        bookToUpdate.setBookDescription("A self development book");
        bookToUpdate.setAuthorName("Robin Sharma");
        bookToUpdate.setPublicationYear(2013);

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            librarySystemService.updateBookDetails(bookId, bookToUpdate);
        });
        verify(bookRepository, times(1)).findById(eq(bookId));
        verify(bookRepository, never()).save(any(Book.class));
    }



}
