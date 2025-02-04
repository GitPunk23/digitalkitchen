package com.digitalkitchen;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@ComponentScan("com.digitalkitchen")
public class DigitalKitchenApplication implements CommandLineRunner
{
    public static void main( String[] args )
    {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(DigitalKitchenApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }
}
