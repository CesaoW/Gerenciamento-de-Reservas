package desafio.programacao.ReservaRestaurante.service;

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

    public User RegisterUser(String name, String password, User.UserRole role, String email){
        String criptedPassword = passwordEncoder.encode(password);
        User user = new User(name, criptedPassword, role, email);
        return userRepository.save(user);
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

    public boolean authenticate(String email, String rawPassword) {
        // 1. Encontrar o usuário pelo email
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false; // Usuário não encontrado ou credenciais inválidas
    }

}
