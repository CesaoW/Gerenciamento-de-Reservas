package desafio.programacao.ReservaRestaurante.dto.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {
        @NotBlank(message = "O email é obrigatório para o login.")
        @Email(message = "O email deve ter um formato válido.")
        private String email;

        @NotBlank(message = "A senha é obrigatória para o login.")
        private String password;

        // Construtor, Getters e Setters
        public UserLoginDTO() {}

        public UserLoginDTO(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
}
