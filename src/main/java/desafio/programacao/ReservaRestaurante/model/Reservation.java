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

    public enum Status{
        ATIVO, CANCELADO;
    }
    @Column(length = 20)
    private Status status;

    //Criando os construtores
    public Reservation() {};
    public Reservation(RestaurantTable table, User user, LocalDateTime dateTime, Status status){
        this.user = user;
        this.table = table;
        this.dateTime = dateTime;
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

