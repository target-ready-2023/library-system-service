package com.target.ready.library.system.service.LibrarySystemService.service;

import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserProfile;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserCatalogRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserCatalogRepository userCatalogRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserCatalogRepository userCatalogRepository, UserRepository userRepository){
        this.userCatalogRepository = userCatalogRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<Integer>> findBooksByUserId(int userId){
        List<UserCatalog> userCatalogs=userCatalogRepository.findByUserId(userId);
        List<Integer> bookIds=new ArrayList<>();
        for(UserCatalog eachUserCatalog:userCatalogs){
            int bookId= eachUserCatalog.getBookId();
            bookIds.add(bookId);
        }
        return new ResponseEntity<>(bookIds, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteBookByUserId(int userId, int bookId){
        userCatalogRepository.deleteByBookIdAndUserId(bookId,userId);
        return new ResponseEntity<>("Book Deleted Successfully",HttpStatus.OK);
    }

    public ResponseEntity<UserCatalog> addUserCatalog(UserCatalog userCatalog){
        return new ResponseEntity<>(userCatalogRepository.save(userCatalog),HttpStatus.OK);

    }

    public ResponseEntity<UserProfile> addUser(UserProfile userProfile){

        return new ResponseEntity<>(userRepository.save(userProfile),HttpStatus.OK);
    }
}
