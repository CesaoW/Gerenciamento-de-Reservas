package desafio.programacao.ReservaRestaurante.entity;

import jakarta.persistence.*;

@Table(name = "User")
@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String passowrd;

    @Enumerated(EnumType.STRING)
    private Role role;
}

