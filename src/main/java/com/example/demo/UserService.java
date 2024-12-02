package com.example.demo;
import java.util.List; // import de interfaçe lista
import java.util.Optional;
import java.util.ArrayList; // Importa a classe ArrayList que implementa a interface List para criar uma lista dinâmica.
import org.springframework.stereotype.Service; //Importa a anotação @Service, que marca a classe como um componente de serviço no Spring.

@Service //classe é um bean de serviço, ou seja, um componente que o Spring gerencia e disponibiliza para injeção em outros lugares.
public class UserService {
	List <User> users = new ArrayList <>  (); //ArrayList permite armazenar os objetos User de forma dinâmica.
	
	public List<User> create(User user) {
        validateUser(user);
        users.add(user);
        return users;
    }
	
	  // Validações de email e senha
    private void validateUser(User user) {
        // Verifica se o email já está registrado
        Optional<User> existingUser = users.stream()
                                           .filter(u -> u.email().equals(user.email()))
                                           .findFirst();
        if (existingUser.isPresent()) {
            throw new UserException("Email já está em uso.");
        }

        // Verifica requisitos da senha
        if (!user.password().matches("^(?=.*\\d).{8,}$")) {
            throw new UserException("Senha deve ter pelo menos 8 caracteres e incluir pelo menos um número.");
        }
        
        if (user.username() == null || user.username().isBlank()) {
            throw new UserException("O nome de usuário não pode estar vazio.");
        }
        if (user.email() == null || user.email().isBlank()) {
            throw new UserException("O email não pode estar vazio.");
        }
    }

    // Login com validação de credenciais
    public User login(String email, String password) {
        return users.stream()
                    .filter(u -> u.email().equals(email) && u.password().equals(password))
                    .findFirst()
                    .orElseThrow(() -> new UserException("Email ou senha inválidos."));
    }
}