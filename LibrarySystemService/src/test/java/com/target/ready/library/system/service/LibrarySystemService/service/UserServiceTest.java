package com.target.ready.library.system.service.LibrarySystemService.service;


import com.target.ready.library.system.service.LibrarySystemService.controller.LibraryControllerTest;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserCatalogRepository;
import com.target.ready.library.system.service.LibrarySystemService.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {UserServiceTest.class})
public class UserServiceTest {

    @Mock
    UserCatalogRepository userCatalogRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;
}
