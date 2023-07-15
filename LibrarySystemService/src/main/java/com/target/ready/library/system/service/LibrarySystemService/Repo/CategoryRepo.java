package com.target.ready.library.system.service.LibrarySystemService.Repo;

import com.target.ready.library.system.service.LibrarySystemService.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Iterable> {
}
