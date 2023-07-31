package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserProfile;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserCatalogRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("library/v3/")
public class UserController {

    @Autowired
    private UserCatalogRepository userCatalogRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("user/books/{userId}")
    public List<Integer> findBooksByUserId(@PathVariable int userId){
        List<UserCatalog> userCatalogs=userCatalogRepository.findByUserId(userId);
        List<Integer> bookIds=new ArrayList<>();
        for(UserCatalog eachUserCatalog:userCatalogs){
            int bookId= eachUserCatalog.getBookId();
            bookIds.add(bookId);
        }
        return bookIds;
    }

    @DeleteMapping("user/books/{userId}/{bookId}")
    @Transactional
    public String deleteBookByUserId(@PathVariable int userId, @PathVariable int bookId){
         userCatalogRepository.deleteByBookIdAndUserId(bookId,userId);
         return "Book Deleted Successfully";
    }

    @PostMapping("user/catalog")
    public UserCatalog addUserCatalog(@RequestBody UserCatalog userCatalog){
        return userCatalogRepository.save(userCatalog);

    }

    @PostMapping("user")
    public UserProfile addUser(@RequestBody UserProfile userProfile){
        return userRepository.save(userProfile);
    }
}
