package com.target.ready.library.system.service.LibrarySystemService.controller;

import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserProfile;
import com.target.ready.library.system.service.LibrarySystemService.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserControllerTest.class})
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

   @Test
   public void addUserTest(){
       UserProfile user = new UserProfile();
       user.setUserId(1);
       user.setUserName("Rohit");
       when(userService.addUser(user)).thenReturn(user);
       ResponseEntity<UserProfile> response = userController.addUser(user);
       assertEquals(HttpStatus.CREATED, response.getStatusCode());
   }

   @Test
   public void addUserCatalogTest(){
       UserCatalog user = new UserCatalog();
       user.setBookId(1);
       user.setId(1);
       user.setUserId(1);
       when(userService.addUserCatalog(user)).thenReturn(user);
       ResponseEntity<UserCatalog> response = userController.addUserCatalog(user);
       assertEquals(HttpStatus.CREATED, response.getStatusCode());
   }

    @Test
    public void findBooksByUserIdTest() {
        int userId = 1;
        List<Integer> bookIds = new ArrayList<>();
        bookIds.add(1);
        when(userService.findBooksByUserId(userId)).thenReturn(bookIds);

        ResponseEntity<List<Integer>> response = userController.findBooksByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Integer> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(bookIds, responseBody);
    }

    @Test
    public void deleteBookByUserIdTest(){
        int userId = 3;
        int bookId = 1;
        when(userService.deleteBookByUserId(userId, bookId)).thenReturn(userId);

        ResponseEntity<Integer> response = userController.deleteBookByUserId(userId, bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Integer responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(userId, responseBody);
    }

    @Test
    public void deleteUserTest(){
        int userId = 3;
        when(userService.deleteUser(userId)).thenReturn(userId);

        ResponseEntity<Integer> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        Integer responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(userId, responseBody);
    }

    @Test
    public void findByUserIdTest(){
        int userId = 3;
        UserProfile user = new UserProfile();
        user.setUserId(userId);
        user.setUserName("Rohit");
        when(userService.findByUserId(userId)).thenReturn(user);

        ResponseEntity<UserProfile> response = userController.findByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        UserProfile responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(user, responseBody);
    }

}
