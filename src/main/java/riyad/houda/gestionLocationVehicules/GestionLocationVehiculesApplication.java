package riyad.houda.gestionLocationVehicules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionLocationVehiculesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionLocationVehiculesApplication.class, args);
		System.out.println("=========================================");
		System.out.println("Application démarrée sur le port 8085");
		System.out.println("Swagger: http://localhost:8085/swagger-ui.html");
		System.out.println("H2 Console: http://localhost:8085/h2-console");
		System.out.println("=========================================");
	}
}
