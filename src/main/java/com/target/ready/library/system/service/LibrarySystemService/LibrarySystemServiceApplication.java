package com.target.ready.library.system.service.LibrarySystemService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.validation.annotation.Validated;


@SpringBootApplication
@Validated
@EntityScan(basePackages = "com.target.ready.library.system.service.LibrarySystemService.entity")
public class LibrarySystemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemServiceApplication.class, args);
	}

}
