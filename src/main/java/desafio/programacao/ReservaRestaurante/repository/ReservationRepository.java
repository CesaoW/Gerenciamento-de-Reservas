package desafio.programacao.ReservaRestaurante.repository;

import desafio.programacao.ReservaRestaurante.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long>{
    Optional<Reservation> findByUser(User user);
    Optional<Reservation> findByUserAndDateTime(User user, LocalDateTime dateTime);
    Optional<Reservation> findByUserAndStatus(User user, Reservation.Status status);
    Optional<Reservation> findByDateTime(LocalDateTime dateTime);
    Optional<Reservation> findByStatus(Reservation.Status status);
    boolean existsByTableAndDateTimeAndStatus(RestaurantTable table, LocalDateTime dateTime, Reservation.Status status);
}
