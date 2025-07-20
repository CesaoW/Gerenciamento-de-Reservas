package desafio.programacao.ReservaRestaurante.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "reservation")
@Entity(name = "Reservation")
public abstract class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    @Column(name ="num_guests")
    private Integer numGuests;

    @Column(nullable = false) // uma reserva deve ter data e hora
    private LocalDateTime dateTime;

    public enum Status{
        ATIVO, CANCELADO;
    }
    @Column(length = 20)
    private Status status;

    //Criando os construtores
    public Reservation() {};
    public Reservation(RestaurantTable table, User user, Integer numGuests, LocalDateTime dateTime, Status status){
        this.user = user;
        this.table = table;
        this.numGuests = numGuests;
        this.dateTime = dateTime;
        this.status = status;
    }

    //Metodos da Reserva
    public abstract String getType();
    public abstract double calculateDiscount();

    //getters

    public long getId() {return id;}
    public User getUser() {
        return user;
    }
    public RestaurantTable getTable() {return table;}
    public Integer getNumGuests() {return numGuests;}
    public LocalDateTime getDateTime() {return dateTime;}

    public Status getStatus(){return status;}

    //setters
    public void setUser(User user) {this.user = user;}
    public void setTable(RestaurantTable table) {
        this.table = table;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}

