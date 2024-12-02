package com.example.demo; //Define o pacote onde esta classe está localizad

import org.springframework.boot.SpringApplication; //Importa a classe SpringApplication, que fornece métodos para iniciar a aplicação Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication; //Importa a anotação @SpringBootApplication, que habilita várias funcionalidades do Spring Boot

@SpringBootApplication //arca esta classe como o ponto de entrada principal da aplicação Spring Boot.
public class DemoApplication { // usa esta classe para inicializar o contexto da aplicação.

	public static void main(String[] args) { //Método principal 
		SpringApplication.run(DemoApplication.class, args); //Inicia a aplicação Spring Boot
	}

}
