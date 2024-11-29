package com.pichincha.exchange.config;

import io.r2dbc.spi.ConnectionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFlux
public class AppConfig {

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory){
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }

    @Bean
    public WebClient webClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl("https://gorest.co.in/public/v2/users")
                //.defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient;
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
