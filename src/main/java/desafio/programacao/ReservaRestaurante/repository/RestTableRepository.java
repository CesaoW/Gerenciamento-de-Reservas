package desafio.programacao.ReservaRestaurante.repository;

import desafio.programacao.ReservaRestaurante.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RestTableRepository extends JpaRepository<RestaurantTable,Long> {
    Optional<RestaurantTable> findByCapacity(int capacity);
    Optional<RestaurantTable> findByTableNumber(int tableNumber);
    Optional<RestaurantTable> findByStatus(RestaurantTable.TableStatus status);
}
