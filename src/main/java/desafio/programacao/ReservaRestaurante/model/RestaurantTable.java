package desafio.programacao.ReservaRestaurante.model;

import jakarta.persistence.*;

@Table(name = "restaurant_tables")
@Entity(name = "RestaurantTable")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "table_number", unique = true, nullable = false)
    private Integer tableNumber;

    @Column(nullable = false)
    private Integer capacity;

    public enum TableStatus{
        DISPONIVEL, RESERVADA, INATIVA
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TableStatus status;

    // construtores
    public RestaurantTable(Integer tableNumber, Integer capacity, TableStatus status) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
    };

    public RestaurantTable() {}

    //Getters
    public long getTableId() {return id;}
    public Integer getTableNumber() {
        return tableNumber;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public TableStatus getStatus() {
        return status;
    }

    //Setters
    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public void setStatus(TableStatus status) {
        this.status = status;
    }

}