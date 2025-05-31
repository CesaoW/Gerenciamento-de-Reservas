package desafio.programacao.ReservaRestaurante.model;

import jakarta.persistence.*;

@Table(name = "restaurant_tables")
@Entity(name = "RestaurantTable")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "table_number", unique = true, nullable = false) // Mais descritivo e garantindo unicidade e não nulo
    private long tableNumber;

    @Column(nullable = false)
    private int capacity;

    @Column(length = 50)
    private String status;
}