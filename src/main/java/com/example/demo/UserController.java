package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public List<User> registerUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody UserLoginRequest request) {
        return userService.login(request.email(), request.password());
    }

    // Captura exceções e retorna erro personalizado
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

// Classe auxiliar para requisição de login
record UserLoginRequest(String email, String password) { }
