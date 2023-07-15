package com.target.ready.library.system.service.LibrarySystemService.Repository;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
