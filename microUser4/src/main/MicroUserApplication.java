package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"main.Repositories", "main.Services", "main.Objects", "main.DTOs", "main.Controllers"})
public class MicroUserApplication {
	public static void main (String[] args) {
		SpringApplication.run(MicroUserApplication.class, args);
	}
}