package com.example.demo;

import java.awt.*;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openSwaggerUi() {
        String swaggerUrl = "http://localhost:8080/swagger-ui.html";

        if (Desktop.isDesktopSupported() && !GraphicsEnvironment.isHeadless()) {
            try {
                Desktop.getDesktop().browse(new URI(swaggerUrl));
                System.out.println("Swagger UI opened in browser: " + swaggerUrl);
            } catch (Exception e) {
                System.out.println("Failed to open browser: " + e.getMessage());
            }
        } else {
            System.out.println("Headless mode detected. Open Swagger UI manually:");
            System.out.println(swaggerUrl);
        }
    }
}
