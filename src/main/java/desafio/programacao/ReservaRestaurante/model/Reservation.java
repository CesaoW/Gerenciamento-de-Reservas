package desafio.programacao.ReservaRestaurante.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "reservation")
@Entity(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    @Column(nullable = false) // uma reserva deve ter data e hora
    private LocalDateTime dateTime;

    @Column(length = 50)
    private String status;

    //Criando o construtor
    public Reservation() {};

}
