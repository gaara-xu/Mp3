package com.gaara.mp3;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient
public class Mp3Application {

	public static void main(String[] args) {
		SpringApplication.run(Mp3Application.class, args);
	}
}
