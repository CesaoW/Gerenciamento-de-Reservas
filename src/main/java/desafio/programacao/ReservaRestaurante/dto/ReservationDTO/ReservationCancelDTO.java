package desafio.programacao.ReservaRestaurante.dto.ReservationDTO;

import desafio.programacao.ReservaRestaurante.model.Reservation;
import desafio.programacao.ReservaRestaurante.model.User;

import java.time.LocalDateTime;

public class ReservationCancelDTO {
    private String userName;
    private LocalDateTime dateTime;

    public ReservationCancelDTO(){};

    public String getUserName(){return userName;}
    public LocalDateTime getDateTime(){return dateTime;}

}
