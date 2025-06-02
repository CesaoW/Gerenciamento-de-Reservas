package desafio.programacao.ReservaRestaurante.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table(name = "users")
@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "O email deve ter um formato válido.")
    @Column(unique = true, length = 255)
    private String email;

    @NotBlank(message =" A senha não deve estar em branco.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "role", length = 50)
    private String role;
}

