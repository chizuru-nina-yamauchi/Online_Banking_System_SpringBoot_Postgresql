package com.example.online_banking_system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *  EncoderConfig class is a configuration class that defines a bean for password encoding.
 *  It provides a BCryptPasswordEncoder bean that can be used throughout the application
 *  to encode and verify passwords securely.

 */



@Configuration // Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
public class EncoderConfig {

    @Bean // Marks this method as a bean producer, meaning the method returns an object that should be registered as a bean in the Spring application context.
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(); // Create a new BCryptPasswordEncoder instance
    }
}
