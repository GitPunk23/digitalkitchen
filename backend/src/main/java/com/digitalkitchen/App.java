package com.digitalkitchen;

import com.digitalkitchen.service.RecipesService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableJpaRepositories("com.digitalkitchen.repository")
@ComponentScan("com.digitalkitchen.service")
@ComponentScan("com.digitalkitchen.controller")
public class App implements CommandLineRunner
{

    @Autowired
    RecipesService rs;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
    }
}
