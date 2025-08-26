package desafio.programacao.ReservaRestaurante.controller;

import desafio.programacao.ReservaRestaurante.dto.ReservationDTO.*;
import desafio.programacao.ReservaRestaurante.model.Reservation;
import desafio.programacao.ReservaRestaurante.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Tag(name= "Reservas", description = "Gerenciador de Reservas")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping()
    @Operation(summary = "Cadastrar reservas", description = "Cadastra uma nova reserva de um usuário")
    public ResponseEntity<?> registerReservation(@RequestBody ReservationRegisterDTO registerDTO){
        try{
            Reservation newReservation = reservationService.registerReservation(registerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @Operation(summary = "Lista de reservas", description = "Retorna uma lista com todas as reservas exitentes na base de dados")
    public ResponseEntity<List<ReservationResponseDTO>>getAllReservation(){
        List<ReservationResponseDTO> allReservations = reservationService.getAllReservations();
        return ResponseEntity.ok(allReservations);
    }

    @PatchMapping("/cancel")
    @Operation(summary = "Cancelar reservas", description = "O usuario pode cancelar uma reserva que está ativa")
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

    @DeleteMapping("/{name}")
    @Operation(summary = "Exclusão de uma reserva", description = "Apenas administradores conseguem apagar da base de dados uma reserva ativa")
    public ResponseEntity<String> deleteByName(@PathVariable String name){
        try{
            reservationService.deleteReservationByName(name);
            return ResponseEntity.ok("Reserva de "+name+" deletada com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva de "+name+" não encontrada.");
        }
    }

}
