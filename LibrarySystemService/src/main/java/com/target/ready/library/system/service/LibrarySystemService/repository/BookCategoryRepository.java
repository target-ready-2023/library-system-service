package com.target.ready.library.system.service.LibrarySystemService.repository;

import com.target.ready.library.system.service.LibrarySystemService.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory,Integer> {
    BookCategory findByBookId(int bookId);
    List<BookCategory> findByCategoryName(String categoryName);
    void deleteBookCategoriesByBookId(int bookId);
    List<BookCategory> findAllCategoriesByBookId(int bookId);
}
