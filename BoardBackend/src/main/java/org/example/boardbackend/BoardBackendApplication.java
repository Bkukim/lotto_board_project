package org.example.boardbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BoardBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardBackendApplication.class, args);
	}

}
