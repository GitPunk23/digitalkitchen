package com.digitalkitchen;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@ComponentScan("com.digitalkitchen")
public class DigitalKitchenApplication implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(DigitalKitchenApplication.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
    }
}
