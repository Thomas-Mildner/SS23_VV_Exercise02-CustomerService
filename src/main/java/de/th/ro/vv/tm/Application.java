package de.th.ro.vv.tm;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
        info = @Info(
                title = "Customer Service",
                version = "1.0",
                description = "Customer service to manage all customer operations",
                contact = @Contact(url = "https://hub.docker.com/r/vvthromildner/ss23_exercise02_customer_service", name = "Available Docker Image")
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}