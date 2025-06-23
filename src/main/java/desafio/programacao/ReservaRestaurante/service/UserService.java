package desafio.programacao.ReservaRestaurante.service;

import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserRegisterDTO;
import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserResponseDTO;
import desafio.programacao.ReservaRestaurante.model.User;
import desafio.programacao.ReservaRestaurante.repository.UserRepository;
import org.hibernate.dialect.lock.OptimisticForceIncrementLockingStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; //criptografar as senhas

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {
        User.UserRole role;
        try {
            role = User.UserRole.valueOf(userRegisterDTO.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Função do usuário inválido " + userRegisterDTO.getRole());
        }
        String hashedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());

        //criando o entity user a partir do DTO e da senha hashed
        User newUserEntity = new User(userRegisterDTO.getName(), hashedPassword, role, userRegisterDTO.getEmail());

        User savedUser = userRepository.save(newUserEntity);

        return new UserResponseDTO(savedUser);
    }

    public Optional<UserResponseDTO> authenticate(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User userEntity = userOptional.get();
            if (passwordEncoder.matches(rawPassword, userEntity.getPassword())) {
                // Autenticado com sucesso, retorna DTO de resposta
                return Optional.of(new UserResponseDTO(userEntity));
            }
        }
        return Optional.empty();
    }

    public Optional<UserResponseDTO> findUserResponseByEmail(String email){
        return userRepository.findByEmail(email)
        .map(UserResponseDTO::new);
    }

    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> findByRole(User.UserRole role){
        return userRepository.findByRole(role);
    }

    public Optional<User> findByEmail(String email){
       return userRepository.findByEmail(email);
    }

}
