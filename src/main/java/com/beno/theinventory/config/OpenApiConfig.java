package com.beno.theinventory.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Inventory API",
                version = "1.0.0",
                description = "Manage products, warehouses, suppliers"
        ),
        servers = @Server(url = "/")
)
public class OpenApiConfig { }
