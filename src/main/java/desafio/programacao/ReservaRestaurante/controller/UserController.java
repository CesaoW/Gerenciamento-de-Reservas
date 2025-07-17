package desafio.programacao.ReservaRestaurante.controller;

import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserLoginDTO;
import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserRegisterDTO;
import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserResponseDTO;
import desafio.programacao.ReservaRestaurante.security.JwtUtil;
import desafio.programacao.ReservaRestaurante.service.UserService;
import jakarta.validation.Valid;
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
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registro")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO registerDTO) {
        try {
            UserResponseDTO newUser = userService.registerUser(registerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // me retorna 201, com usuario criado
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);// captura roles invalidas, lançadas pelo Service
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO loginDTO) {
        Optional<UserResponseDTO> userOptional = userService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());

        if (userOptional.isPresent()) {
            UserResponseDTO user = userOptional.get();
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(Map.of("message", "Login bem-sucedido", "token", token, "role", user.getRole().name()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
    }

}
