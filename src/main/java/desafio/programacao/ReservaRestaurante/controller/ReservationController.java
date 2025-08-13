package desafio.programacao.ReservaRestaurante.controller;

import desafio.programacao.ReservaRestaurante.dto.ReservationDTO.*;
import desafio.programacao.ReservaRestaurante.dto.RestTableDTO.RestTableResponseDTO;
import desafio.programacao.ReservaRestaurante.model.Reservation;
import desafio.programacao.ReservaRestaurante.model.User;
import desafio.programacao.ReservaRestaurante.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping()
    public ResponseEntity<?> registerReservation(@RequestBody ReservationRegisterDTO registerDTO){
        try{
            Reservation newReservation = reservationService.registerReservation(registerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<ReservationResponseDTO>>getAllReservation(){
        List<ReservationResponseDTO> allReservations = reservationService.getAllReservations();
        return ResponseEntity.ok(allReservations);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteByName(@PathVariable String name){
        try{
            reservationService.deleteReservationByName(name);
            return ResponseEntity.ok("Reserva de "+name+" deletada com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva de "+name+" não encontrada.");
        }
    }


    @PatchMapping("/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(@RequestBody ReservationCancelDTO calcelDTO) {
        try {
            ReservationResponseDTO cancelledReservation = reservationService.cancelReservation(calcelDTO);
            return ResponseEntity.ok(cancelledReservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
