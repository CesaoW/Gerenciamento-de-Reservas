package desafio.programacao.ReservaRestaurante.controller;

import desafio.programacao.ReservaRestaurante.model.User;
import desafio.programacao.ReservaRestaurante.security.JwtUtil;
import desafio.programacao.ReservaRestaurante.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        // Validação campos existentes
        String name = request.get("name");
        String password = request.get("password");
        String email = request.get("email");
        String roleString = request.get("role");

        if (name == null || password == null || email == null || roleString == null) {
            return ResponseEntity.badRequest().body("Todos os campos (name, password, email, role) são obrigatórios.");
        }

        User.UserRole role;
        try {
            role = User.UserRole.valueOf(roleString.toUpperCase()); // Converta para maiúsculas para corresponder ao enum
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role inválido. Use ADMINISTRADOR ou CLIENTE.");
        }

        try {
            User newUser = userService.RegisterUser(name, password, role, email);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // Retorna 201 Created com o usuário criado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email e senha são obrigatórios.");
        }

       boolean isAuthenticated = userService.authenticate(email, password);

        if (isAuthenticated) {
            // Se a autenticação foi bem-sucedida, encontre o usuário para obter detalhes
            Optional<User> userOptional = userService.findByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String token = JwtUtil.generateToken(user.getEmail());

                return ResponseEntity.ok(Map.of("message", "Login bem-sucedido", "token", token, "role", user.getRole().name()));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");

    }

}
