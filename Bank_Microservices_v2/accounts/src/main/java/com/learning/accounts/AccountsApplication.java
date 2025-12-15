package com.learning.accounts;

import com.learning.accounts.dto.AccountsContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.ExternalDocumentation;

@SpringBootApplication
@EnableConfigurationProperties(value={AccountsContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info= @Info(
				title = "Accounts Microservice REST API Documentation",
				version = "1.0",
				description = "Ram Bank Account Microservice REST API Documentation",
				contact=@Contact(
						name="Sriram Appana",
				        email="sriram22101998@gmail.com"),
				license =@License(name="Apache 2.0",
						url="https://github.com/sriram22101998")


		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts Microservice REST API Documentation",
				url = "http://localhost:8080/swagger-ui/index.html")
)
public class AccountsApplication {

/* <<<<<<<<<<<<<<  ✨ Windsurf Command 🌟 >>>>>>>>>>>>>>>> */
	/**
	 * The main entry point for the application.
	 * @param args The command line arguments for the application.
	 */
	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);

	}
/* <<<<<<<<<<  d59981c2-7945-4406-b045-2d5e2876dbc0  >>>>>>>>>>> */

}
