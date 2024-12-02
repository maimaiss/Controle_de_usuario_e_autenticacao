package com.example.demo; //Define o pacote onde está localizada a classe User.

public record User(String username, String email, String password) {

}

//record é uma funcionalidade do Java (Java 14+ Preview e Java 16+ final) que cria uma classe imutável(uma vez que definidos não podem ser alterados).
//A classe User possui dois campos: username e password.
//O record fornece automaticamente:
//Getters para username e password.
//toString(), equals() e hashCode().