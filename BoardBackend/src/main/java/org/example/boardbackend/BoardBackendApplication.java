package org.example.boardbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableCaching
@EnableScheduling
public class BoardBackendApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(BoardBackendApplication.class, args);
		}catch (Exception e){
			log.debug("테스트"+e.getMessage());
		}
	}

}
