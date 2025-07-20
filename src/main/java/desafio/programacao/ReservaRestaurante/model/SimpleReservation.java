package desafio.programacao.ReservaRestaurante.model;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("SIMPLE")
public class SimpleReservation extends Reservation{
    public SimpleReservation() {
        super();
    }

    public SimpleReservation(RestaurantTable table, User user, Integer numGuests, LocalDateTime dateTime, Status status) {
        super(table, user, numGuests, dateTime, status);
    }

    @Override
    public String getType() {
        return "Simples";
    }

    @Override
    public double calculateDiscount() {
        return 0.0;
    }
}
