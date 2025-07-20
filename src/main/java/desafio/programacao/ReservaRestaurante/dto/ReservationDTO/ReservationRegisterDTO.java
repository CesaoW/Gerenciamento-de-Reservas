package desafio.programacao.ReservaRestaurante.dto.ReservationDTO;

import desafio.programacao.ReservaRestaurante.model.User;

import java.time.LocalDateTime;

public class ReservationRegisterDTO {
        private String userName;
        private Integer numGuest;
        private LocalDateTime dateTime;
        private String reservationType;
        private String vipServices;

        public String getUserName(){return userName;}
        public Integer getNumGuest(){return numGuest;}
        public LocalDateTime getDateTime(){return dateTime;}
        public String getReservationType(){return reservationType;}
        public String getVipServices(){return vipServices;}

        public void setUserName(String userName){this.userName = userName;}
        public void setNumGuest(Integer numGuest){this.numGuest = numGuest;}
        public void setDateTime(LocalDateTime dateTime){this.dateTime = dateTime;}
        public void setReservationType(String reservationType){this.reservationType = reservationType;}
        public void setVipServices(String vipServices){this.vipServices = vipServices;}
}
