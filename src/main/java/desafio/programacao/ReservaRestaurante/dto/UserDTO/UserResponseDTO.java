package desafio.programacao.ReservaRestaurante.dto.UserDTO;

import desafio.programacao.ReservaRestaurante.model.User;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private User.UserRole role;

    // Construtor a partir da Entidade User
    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    // Construtor vazio para desserialização
    public UserResponseDTO() {}

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public User.UserRole getRole() { return role; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(User.UserRole role) { this.role = role; }
}

