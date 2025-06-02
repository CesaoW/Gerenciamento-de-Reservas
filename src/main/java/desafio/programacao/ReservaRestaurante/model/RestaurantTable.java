package desafio.programacao.ReservaRestaurante.model;

import jakarta.persistence.*;

import javax.swing.text.TabableView;

@Table(name = "restaurant_tables")
@Entity(name = "RestaurantTable")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "table_number", unique = true, nullable = false)
    private long tableNumber;

    @Column(nullable = false)
    private int capacity;


    public enum TableStatus{
        DISPONIVEL, RESERVADA, INATIVA
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TableStatus status;

    // construtores
    public RestaurantTable() {};
    public RestaurantTable(long tableNumber, int capacity){
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = TableStatus.DISPONIVEL;
    }

    // Criando os getters e setters
    public void setTableNumber(long tableNumber) {
        this.tableNumber = tableNumber;
    }
    public long getTableNumber() {
        return tableNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }
    public TableStatus getStatus() {
        return status;
    }
}