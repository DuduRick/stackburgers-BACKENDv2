package com.stackburgers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal do Stack Burgers
 * Ponto de entrada da aplicação Spring Boot
 */
@SpringBootApplication
public class StackBurgersApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackBurgersApplication.class, args);
        System.out.println("===========================================");
        System.out.println("  Stack Burgers API rodando na porta 8080");
        System.out.println("  http://localhost:8080/api");
        System.out.println("===========================================");
    }
}
