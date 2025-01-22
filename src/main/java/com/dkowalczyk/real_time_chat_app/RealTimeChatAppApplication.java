package com.dkowalczyk.real_time_chat_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
public class RealTimeChatAppApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RealTimeChatAppApplication.class);

		app.setDefaultProperties(Collections.singletonMap("spring.profiles.default", "dev"));
		app.run(args);
	}

}
