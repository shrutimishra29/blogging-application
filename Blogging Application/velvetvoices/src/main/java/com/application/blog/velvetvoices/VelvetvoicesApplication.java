package com.application.blog.velvetvoices;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Velvetvoices API", version = "1.0", description = "This is a blogging application", contact = @Contact(name = "Shruti Mishra", email="misshrutimishra@gmail.com")))
@SecurityScheme(name= "velvetvoiceapi", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT" , in = SecuritySchemeIn.HEADER)
public class VelvetvoicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VelvetvoicesApplication.class, args);
	}

}
