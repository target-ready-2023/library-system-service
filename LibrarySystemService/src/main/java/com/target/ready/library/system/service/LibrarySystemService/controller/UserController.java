package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserProfile;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserCatalogRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserRepository;
import com.target.ready.library.system.service.LibrarySystemService.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("library/v3/")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("user/books/{userId}")
    public List<Integer> findBooksByUserId(@PathVariable int userId){
       return userService.findBooksByUserId(userId);
    }

    @DeleteMapping("user/books/{userId}/{bookId}")
    @Transactional
    public String deleteBookByUserId(@PathVariable int userId, @PathVariable int bookId){
         return userService.deleteBookByUserId(userId, bookId);
    }

    @PostMapping("user/catalog")
    public UserCatalog addUserCatalog(@RequestBody UserCatalog userCatalog){
        return userService.addUserCatalog(userCatalog);

    }

    @PostMapping("user")
    public UserProfile addUser(@RequestBody UserProfile userProfile){
        return userService.addUser(userProfile);
    }
}
