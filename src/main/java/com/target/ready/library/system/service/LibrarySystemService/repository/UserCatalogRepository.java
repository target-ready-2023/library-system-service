package com.target.ready.library.system.service.LibrarySystemService.repository;

import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCatalogRepository extends JpaRepository<UserCatalog,Integer> {
    @Modifying
    @Query("DELETE FROM UserCatalog b WHERE b.bookId = :bookId AND b.userId = :userId")
    public Integer deleteByBookIdAndUserId(int bookId, int userId);


    public UserCatalog findByBookIdAndUserId(int bookId, int userId);
    public List<UserCatalog> findByUserId(int userId);
}
