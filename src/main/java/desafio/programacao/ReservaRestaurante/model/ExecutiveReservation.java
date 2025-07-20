package desafio.programacao.ReservaRestaurante.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("EXECUTIVE") // mostrando no DB
public class ExecutiveReservation extends Reservation{

        @Column(name = "vip_amenities")
        private String vipServices; //para reservas executivas

        public ExecutiveReservation() {super();}

        public ExecutiveReservation(RestaurantTable table, User user, Integer numGuests, LocalDateTime dateTime, Status status, String vipServices) {
            super(table, user, numGuests, dateTime, status);
            System.out.println(vipServices);
            this.vipServices = vipServices;
        }

        @Override
        public String getType() {return "Executiva";}

        @Override
        public double calculateDiscount() {return 0.10;}

        // Getter e Setter para vipAmenities
        public String getVipServices() {return vipServices;}
        public void setVipServices(String vipServices) {this.vipServices = vipServices;}
}

