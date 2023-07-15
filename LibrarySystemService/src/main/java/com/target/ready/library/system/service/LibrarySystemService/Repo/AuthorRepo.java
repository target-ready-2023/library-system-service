package com.target.ready.library.system.service.LibrarySystemService.Repo;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
