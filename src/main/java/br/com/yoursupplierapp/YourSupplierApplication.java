package br.com.yoursupplierapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(info = @Info(title = "Your Supplier Application", version = "1.0.0"))
@SpringBootApplication
public class YourSupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourSupplierApplication.class, args);
	}

}
