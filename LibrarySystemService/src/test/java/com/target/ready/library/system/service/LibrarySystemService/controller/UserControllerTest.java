package com.target.ready.library.system.service.LibrarySystemService.controller;


import com.target.ready.library.system.service.LibrarySystemService.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {UserControllerTest.class})
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;
}
