package com.apispotify.API.Spotify;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.apispotify.API")
public class ApiSpotifyApplication {
	public static void main(String[] args) {
		System.out.println("Iniciando API");
		SpringApplication.run(ApiSpotifyApplication.class, args);

		System.out.println("API Rodando...");
	}
}
