package com.learning.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.cards.controller") })
@EnableJpaRepositories("com.eazybytes.cards.repository")
@EntityScan("com.eazybytes.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "RamBank Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Sriram",
                        email = "sriram22101998@gmail.com",
                        url = "www.linkedin.com/in/sriram-ram-01a6bb182"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "www.linkedin.com/in/sriram-ram-01a6bb182"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "RamBank Loans microservice REST API Documentation",
                url = "https://www.Ram.com/swagger-ui.html"
        )
)
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }
}