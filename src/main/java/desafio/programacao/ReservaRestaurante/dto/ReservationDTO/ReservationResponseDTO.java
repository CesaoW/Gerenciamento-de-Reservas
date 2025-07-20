package desafio.programacao.ReservaRestaurante.dto.ReservationDTO;

import desafio.programacao.ReservaRestaurante.dto.RestTableDTO.RestTableResponseDTO;
import desafio.programacao.ReservaRestaurante.dto.UserDTO.UserResponseDTO;
import desafio.programacao.ReservaRestaurante.model.*;

import java.time.LocalDateTime;

public class ReservationResponseDTO {
    private long id;
    private UserResponseDTO user;
    private RestTableResponseDTO table;
    private Integer numGuest;
    private LocalDateTime dateTime;
    private String reservationType;
    private String vipServices;

    public ReservationResponseDTO(){};
    public ReservationResponseDTO(Reservation reservation){
        this.id = reservation.getId();
        this.user = new UserResponseDTO(reservation.getUser());
        this.table = new RestTableResponseDTO(reservation.getTable());
        this.numGuest = reservation.getNumGuests();
        this.dateTime = reservation.getDateTime();
        this.reservationType = reservation.getType();

        if (reservation instanceof ExecutiveReservation) {
            this.vipServices = ((ExecutiveReservation) reservation).getVipServices();
        }
    }

    //getter e setters


    public long getId(){return id;}
    public UserResponseDTO getUser(){return user;}
    public RestTableResponseDTO getTable(){return table;}
    public Integer getNumGuest(){return numGuest;}
    public LocalDateTime getDateTime(){return dateTime;}
    public String getReservationType(){return reservationType;}
    public String getVipServices(){return vipServices;}
}
