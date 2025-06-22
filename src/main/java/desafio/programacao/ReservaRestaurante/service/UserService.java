package desafio.programacao.ReservaRestaurante.service;

import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserRegisterDTO;
import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserResponseDTO;
import desafio.programacao.ReservaRestaurante.model.User;
import desafio.programacao.ReservaRestaurante.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; //criptografar as senhas

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponseDTO RegisterUser(UserRegisterDTO userRegisterDTO) {
        User.UserRole role;
        try {
            role = User.UserRole.valueOf(userRegisterDTO.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Função do usuário inválido " + userRegisterDTO.getRole());
        }
        String hassedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());

        //criando o entity user a partir do DTO e da senha hashed
        User newUserEntity = new User(userRegisterDTO.getName(), hassedPassword, role, userRegisterDTO.getEmail());

        User savedUser = userRepository.save(newUserEntity);

        return new UserResponseDTO(savedUser);
    }

    public Optional<UserResponseDTO> User(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email); // Repositório retorna User (entidade)

        if (userOptional.isPresent()) {
            User userEntity = userOptional.get();
            if (passwordEncoder.matches(rawPassword, userEntity.getPassword())) {
                // Autenticado com sucesso, retorna DTO de resposta
                return Optional.of(new UserResponseDTO(userEntity));
            }
        }
        return Optional.empty(); // Credenciais inválidas ou usuário não encontrado
    }

    public Optional<UserRegisterDTO> findByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<UserRegisterDTO> findByRole(UserRegisterDTO role){
        return userRepository.findByRole(role);
    }

    public Optional<UserRegisterDTO> findByEmail(String email){
       return userRepository.findByEmail(email);
    }

    public boolean authenticate(String email, String rawPassword) {
        // 1. Encontrar o usuário pelo email
        Optional<UserRegisterDTO> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserRegisterDTO user = userOptional.get();
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false; // Usuário não encontrado ou credenciais inválidas
    }

}
