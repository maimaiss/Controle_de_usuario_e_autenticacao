package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals; // importa metodo
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List; // importa a interface list

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; //importa anotação para indicar que os metodos são metodos de teste

public class UserServiceTest{

	private UserService userService = new UserService(); //Instancia um objeto da classe UserService que será testado

    @BeforeEach
    public void setUp() {
        userService = new UserService(); // Inicializa o serviço antes de cada teste
    }

    // Teste 1: Cadastro de usuário com dados válidos
    @Test
    public void testCreateUserWithValidData() {
        User user = new User("João Silva", "joao@example.com", "senha123");
        List<User> users = userService.create(user);
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
    }

    // Teste 2: Cadastro de usuário com email duplicado
    @Test
    public void testCreateUserWithDuplicateEmail() {
        User user1 = new User("Maria", "maria@example.com", "senha123");
        userService.create(user1);

        User user2 = new User("Jõao", "maria@example.com", "senha123");

        Exception exception = assertThrows(UserException.class, () -> {
            userService.create(user2);
        });

        assertEquals("Email já está em uso.", exception.getMessage());
    }

    // Teste 3: Cadastro de usuário com senha curta
    @Test
    public void testCreateUserWithShortPassword() {
        User user = new User("Pedro", "pedro@example.com", "abc");

        Exception exception = assertThrows(UserException.class, () -> {
            userService.create(user);
        });

        assertEquals("Senha deve ter pelo menos 8 caracteres e incluir pelo menos um número.", exception.getMessage());
    }

    // Teste 4: Login bem-sucedido com credenciais corretas
    @Test
    public void testLoginWithValidCredentials() {
        User user = new User("Ana", "ana@example.com", "senha1234");
        userService.create(user);

        User loggedInUser = userService.login("ana@example.com", "senha1234");
        assertEquals(user, loggedInUser);
    }

    // Teste 5: Login com credenciais incorretas
    @Test
    public void testLoginWithInvalidPassword() {
        User user = new User("Carlos", "carlos@example.com", "senha1234");
        userService.create(user);

        Exception exception = assertThrows(UserException.class, () -> {
            userService.login("carlos@example.com", "senhaerrada");
        });

        assertEquals("Email ou senha inválidos.", exception.getMessage());
    }

    // Teste 6: Login com email não cadastrado
    @Test
    public void testLoginWithUnregisteredEmail() {
        Exception exception = assertThrows(UserException.class, () -> {
            userService.login("naoexiste@example.com", "senha123");
        });

        assertEquals("Email ou senha inválidos.", exception.getMessage());
    }

     // Teste 7: Cadastro com nome de usuário vazio
     @Test
     public void testCreateUserWithEmptyUsername() {
         User user = new User("", "vazio@example.com", "senha123");
 
         Exception exception = assertThrows(UserException.class, () -> {
             userService.create(user);
         });
 
         assertEquals("O nome de usuário não pode estar vazio.", exception.getMessage());
     }
 
     // Teste 8: Cadastro com email vazio
     @Test
     public void testCreateUserWithEmptyEmail() {
         User user = new User("Teste", "", "senha123");
 
         Exception exception = assertThrows(UserException.class, () -> {
             userService.create(user);
         });
 
         assertEquals("O email não pode estar vazio.", exception.getMessage());
     }
}