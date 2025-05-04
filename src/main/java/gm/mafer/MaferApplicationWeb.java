package gm.mafer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "Mafer API",
        version = "2.0",
        description = "API Rest para Mafer desarrollada en Spring Boot 3 y documentada con Swagger"
))

@SpringBootApplication
public class MaferApplicationWeb {

    public static void main(String[] args) {
        SpringApplication.run(MaferApplicationWeb.class, args);


    }
}
