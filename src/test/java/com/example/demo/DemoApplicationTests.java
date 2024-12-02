package com.example.demo;

import org.junit.jupiter.api.Test; // importa da Junit a bliblioteca de testes
import org.springframework.boot.test.context.SpringBootTest; // Simula o ambiente da aplicação real, carregando todos os beans e permitindo testes de integração

@SpringBootTest //Declara que esta classe é um teste de integração Spring Boot
class DemoApplicationTests { //declara a classe de testes 

	@Test // marca o metodo abaixo como um metodo de teste
	void contextLoads() { // testa se a aplicação é carregada corretamente
	}

}
