package com.target.ready.library.system.service.LibrarySystemService.service;


import com.target.ready.library.system.service.LibrarySystemService.controller.LibraryControllerTest;
import com.target.ready.library.system.service.LibrarySystemService.entity.Book;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserCatalog;
import com.target.ready.library.system.service.LibrarySystemService.entity.UserProfile;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceAlreadyExistsException;
import com.target.ready.library.system.service.LibrarySystemService.exceptions.ResourceNotFoundException;
import com.target.ready.library.system.service.LibrarySystemService.repository.BookRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserCatalogRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {UserServiceTest.class})
public class UserServiceTest {

    @Mock
    UserCatalogRepository userCatalogRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    BookRepository bookRepository;

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

        List<UserCatalog> mockUserCatalogs = new ArrayList<>();
        mockUserCatalogs.add(new UserCatalog());
        mockUserCatalogs.add(new UserCatalog());
        when(userCatalogRepository.findByUserId(userId)).thenReturn(mockUserCatalogs);
        List<UserCatalog> result = userService.findBooksByUserId(userId);
        verify(userCatalogRepository).findByUserId(userId);
        Assertions.assertEquals(2, result.size());
        }

    @Test
    public void findBooksByUserIdNoBookstest() {
        int userId = 1;
        when(userCatalogRepository.findByUserId(userId)).thenReturn(new ArrayList<>());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.findBooksByUserId(userId);
        });
        verify(userCatalogRepository).findByUserId(userId);
    }



    @Test
    public void deleteBookByUserIdTest(){

        int userId = 1;
        int bookId = 2;

        UserProfile mockUserProfile = new UserProfile();
        when(userRepository.findByUserId(userId)).thenReturn(mockUserProfile);

        Book mockBook = new Book();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        UserCatalog mockUserCatalog = new UserCatalog();
        when(userCatalogRepository.findByBookIdAndUserId(bookId, userId)).thenReturn(mockUserCatalog);

        when(userCatalogRepository.deleteByBookIdAndUserId(bookId, userId)).thenReturn(1);

        Integer result = userService.deleteBookByUserId(userId, bookId);

        verify(userRepository).findByUserId(userId);
        verify(bookRepository).findById(bookId);
        verify(userCatalogRepository).findByBookIdAndUserId(bookId, userId);
        verify(userCatalogRepository).deleteByBookIdAndUserId(bookId, userId);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void deleteBookByUserIdUserNotFoundTest() {
        int userId = 1;
        int bookId = 2;

        when(userRepository.findByUserId(userId)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteBookByUserId(userId, bookId);
        });

        verify(userRepository).findByUserId(userId);

        verifyNoMoreInteractions(bookRepository, userCatalogRepository);
    }

    @Test
    public void deleteBookByUserIdBookNotFoundTest() {
        int userId = 1;
        int bookId = 2;

        UserProfile mockUserProfile = new UserProfile();
        when(userRepository.findByUserId(userId)).thenReturn(mockUserProfile);
        when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteBookByUserId(userId, bookId);
        });
        verify(userRepository).findByUserId(userId);

        verify(bookRepository).findById(bookId);

        verifyNoMoreInteractions(userCatalogRepository);
    }

    @Test
    public void deleteBookByUserIdCatalogNotFoundTest() {
        int userId = 1;
        int bookId = 2;

        UserProfile mockUserProfile = new UserProfile();
        when(userRepository.findByUserId(userId)).thenReturn(mockUserProfile);

        Book mockBook = new Book();
        when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.of(mockBook));

        when(userCatalogRepository.findByBookIdAndUserId(bookId, userId)).thenReturn(null);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteBookByUserId(userId, bookId);
        });

        verify(userRepository).findByUserId(userId);

        verify(bookRepository).findById(bookId);

        verify(userCatalogRepository).findByBookIdAndUserId(bookId, userId);

        verifyNoMoreInteractions(userCatalogRepository);
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
    public void deleteUserNotFoundTest() {
        int userIdToDelete = 1;

        when(userRepository.findByUserId(userIdToDelete)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser(userIdToDelete);
        });

        verify(userRepository).findByUserId(userIdToDelete);

        verifyNoMoreInteractions(userCatalogRepository);
        verifyNoMoreInteractions(userRepository);
    }



    @Test
    public void deleteUserWithBooksTest() {
        int userIdToDelete = 1;

        UserProfile mockUserProfile = new UserProfile();
        when(userRepository.findByUserId(userIdToDelete)).thenReturn(mockUserProfile);

        List<UserCatalog> mockUserCatalogs = new ArrayList<>();
        mockUserCatalogs.add(new UserCatalog());
        when(userCatalogRepository.findByUserId(userIdToDelete)).thenReturn(mockUserCatalogs);
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> {
            userService.deleteUser(userIdToDelete);
        });
        verify(userRepository).findByUserId(userIdToDelete);
        verify(userCatalogRepository).findByUserId(userIdToDelete);
        verify(userRepository, never()).deleteByUserId(userIdToDelete);
    }

    @Test
    public void findByUserIdTest(){
        UserProfile user = new UserProfile();
        user.setUserId(1);
        user.setUserName("Rohit");
        when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
        assert(userService.findByUserId(user.getUserId()).getUserId() == 1);
    }

    @Test
    public void getAllUsersTest() {
        List<UserProfile> mockUsers = new ArrayList<>();
        mockUsers.add(new UserProfile());
        mockUsers.add(new UserProfile());
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<UserProfile> result = userService.getAllUsers();
        verify(userRepository).findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getAllUsersNoUsersTest() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.getAllUsers();
        });
        verify(userRepository).findAll();
    }

}






