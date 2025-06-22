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

    public enum UserRole {
        ADMINISTRADOR, CLIENTE;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private UserRole role;

    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "O email deve ter um formato válido.")
    @Column(unique = true, length = 255)
    private String email;

    @NotBlank(message =" A senha não deve estar em branco.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;


    //construtores
    public User(){}
    public User(String name, String password, UserRole role, String email){
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    //getters e setters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public UserRole getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword(){
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


