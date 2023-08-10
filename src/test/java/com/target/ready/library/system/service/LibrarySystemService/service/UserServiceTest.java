package com.target.ready.library.system.service.LibrarySystemService.service;


import com.target.ready.library.system.service.LibrarySystemService.controller.LibraryControllerTest;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserProfile;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserCatalogRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {UserServiceTest.class})
public class UserServiceTest {

    @Mock
    UserCatalogRepository userCatalogRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void addUserTest(){
        UserProfile user = new UserProfile();
        user.setUserId(1);
        user.setUserName("Rohit");
        when(userRepository.save(user)).thenReturn(user);
        assert(userService.addUser(user).getUserId() == 1);
    }

    @Test
    public void addUserCatalogTest(){

        UserCatalog user = new UserCatalog();
        user.setBookId(1);
        user.setId(1);
        user.setUserId(2);
        UserProfile user1 = new UserProfile();
        user1.setUserId(2);
        user1.setUserName("Rohit");
        when(userRepository.findByUserId(user.getUserId())).thenReturn(user1);
        when(userCatalogRepository.save(user)).thenReturn(user);
        assert(userService.addUserCatalog(user).getUserId() == user1.getUserId());
    }

    @Test
    public void findBooksByUserIdTest(){
        int userId = 1;
        List<UserCatalog> userCatalogs = new ArrayList<>();
        userCatalogs.add(new UserCatalog(1, userId, 1));
        when(userCatalogRepository.findByUserId(userId)).thenReturn(userCatalogs);

        List<Integer> bookIds = userService.findBooksByUserId(userId);

        List<Integer> expectedBookIds = new ArrayList<>();
        expectedBookIds.add(1);

        assertEquals(expectedBookIds, bookIds);
    }

    @Test
    public void deleteBookByUserIdTest(){
        UserCatalog user = new UserCatalog();
        user.setId(1);
        user.setUserId(1);
        user.setBookId(1);
        when(userCatalogRepository.deleteByBookIdAndUserId(user.getBookId(),user.getUserId())).thenReturn(user.getUserId());
        assert(userService.deleteBookByUserId(user.getUserId(),user.getBookId()) == 1);
    }

    @Test
    public void deleteUserTest(){
        int userId = 1;

        when(userRepository.findByUserId(userId)).thenReturn(new UserProfile());
        when(userCatalogRepository.findByUserId(userId)).thenReturn(new ArrayList<>());
        int result = userService.deleteUser(userId);
        verify(userRepository).findByUserId(userId);
        verify(userRepository).deleteByUserId(userId);
        verify(userCatalogRepository).findByUserId(userId);
        assertEquals(userId, result);


    }

    @Test
    public void findByUserIdTest(){
        UserProfile user = new UserProfile();
        user.setUserId(1);
        user.setUserName("Rohit");
        when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
        assert(userService.findByUserId(user.getUserId()).getUserId() == 1);
    }

}






