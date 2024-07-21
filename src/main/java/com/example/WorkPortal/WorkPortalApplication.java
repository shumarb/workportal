/**
 * Main application class for WorkPortal.
 * This class initializes and starts the Spring Boot application.
 */

package com.example.WorkPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkPortalApplication {

	/**
	 * Main method to start the WorkPortalApplication.
	 * It initializes the Spring Boot application and logs startup messages.
	 *
	 * @param args Command-line arguments (not used within application logic).
	 */
	public static void main(String[] args) {
		SpringApplication.run(WorkPortalApplication.class, args);
	}

}
