package com.target.ready.library.system.service.LibrarySystemService.Controller;

import com.target.ready.library.system.service.LibrarySystemService.Service.LibraryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {LibraryControllerTest.class})
public class LibraryControllerTest {

    @Mock
    LibraryService libraryService;

    @InjectMocks
    LibrarySystemController librarySystemController;


}
