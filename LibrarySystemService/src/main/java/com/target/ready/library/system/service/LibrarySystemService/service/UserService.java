package com.target.ready.library.system.service.LibrarySystemService.service;

import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserProfile;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserCatalogRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Integer> findBooksByUserId(int userId){
        List<UserCatalog> userCatalogs=userCatalogRepository.findByUserId(userId);
        List<Integer> bookIds=new ArrayList<>();
        for(UserCatalog eachUserCatalog:userCatalogs){
            int bookId= eachUserCatalog.getBookId();
            bookIds.add(bookId);
        }
        return bookIds;
    }

    public Integer deleteBookByUserId(int userId, int bookId){
        return userCatalogRepository.deleteByBookIdAndUserId(bookId,userId);

    }

    public UserCatalog addUserCatalog(UserCatalog userCatalog){
        return userCatalogRepository.save(userCatalog);

    }

    public UserProfile addUser(UserProfile userProfile){
        return userRepository.save(userProfile);
    }
}
