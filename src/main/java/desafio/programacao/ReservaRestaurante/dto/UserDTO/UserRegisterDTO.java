package desafio.programacao.ReservaRestaurante.dto.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String password;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ter um formato válido.")
    private String email;
    @NotBlank(message = "O papel do usuário (role) é obrigatório.")
    private String role;

    // Construtor, Getters e Setters
    public UserRegisterDTO() {}

    public UserRegisterDTO(String name, String password, String email, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    //Getters
    public String getName() {
        return name;
    }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    //Setters
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}