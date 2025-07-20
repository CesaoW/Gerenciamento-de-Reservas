package desafio.programacao.ReservaRestaurante.service;

import desafio.programacao.ReservaRestaurante.dto.ReservationDTO.*;
import desafio.programacao.ReservaRestaurante.model.*;
import desafio.programacao.ReservaRestaurante.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private RestTableRepository tableRepository;
    private UserRepository userRepository;
    private EntityManager entityManager;

    public ReservationService(ReservationRepository reservationRepository,
                              RestTableRepository restTableRepository,
                              UserRepository userRepository,
                              EntityManager entityManager){
        this.reservationRepository = reservationRepository;
        this.tableRepository = restTableRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    private RestaurantTable findAvailableTable(Integer numGuests, LocalDateTime desiredDateTime, String reservationType) {
       List<RestaurantTable> candidateTables = tableRepository.findByCapacityGreaterThanEqualAndStatus(
                numGuests, RestaurantTable.TableStatus.DISPONIVEL);

        if (candidateTables.isEmpty()) {
            return null;
        }
        for (RestaurantTable table : candidateTables) {
            // Verifique se já existe uma reserva ATIVA para esta mesa no horário exato
            boolean isTableOccupied = reservationRepository.existsByTableAndDateTimeAndStatus(
                    table, desiredDateTime, Reservation.Status.ATIVO
            );

            if (!isTableOccupied) {
                return table; // Mesa disponivel
            }
        }

        return null;
    }

    @Transactional
    public Reservation registerReservation(ReservationRegisterDTO registerDTO) {
        User user = userRepository.findByName(registerDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("User with username '" + registerDTO.getUserName() + "' not found."));

        RestaurantTable availableTable = findAvailableTable(registerDTO.getNumGuest(), registerDTO.getDateTime(), registerDTO.getReservationType());
        if (availableTable == null) {
            throw new RuntimeException("No available table found for the specified criteria.");
        }

        Reservation reservation;
        if ("SIMPLE".equalsIgnoreCase(registerDTO.getReservationType())) {
            reservation = new SimpleReservation(availableTable, user, registerDTO.getNumGuest(), registerDTO.getDateTime(), Reservation.Status.ATIVO);
        } else if ("EXECUTIVE".equalsIgnoreCase(registerDTO.getReservationType())) {
            System.out.println("DEBUG: VIP Services do DTO para ExecutiveReservation: " + registerDTO.getVipServices());
            reservation = new ExecutiveReservation(availableTable, user, registerDTO.getNumGuest(), registerDTO.getDateTime(), Reservation.Status.ATIVO, registerDTO.getVipServices());
        } else {
            throw new IllegalArgumentException("Invalid reservation type: " + registerDTO.getReservationType());
        }

        availableTable.setStatus(RestaurantTable.TableStatus.RESERVADA);
        tableRepository.save(availableTable); //salvando o status da mesa ao fazer uma reserva

        return reservationRepository.save(reservation);
    }

    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponseDTO::new)
                .collect(Collectors.toList());
    }


    public ReservationResponseDTO cancelReservation(ReservationCancelDTO cancelDTO) {
        User user = userRepository.findByName(cancelDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("Usuário '" + cancelDTO.getUserName() + "' não encontrado."));

        Reservation reservation = reservationRepository.findByUserAndDateTime(user, cancelDTO.getDateTime())
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada para o usuário '" + cancelDTO.getUserName() + "' na data/hora " + cancelDTO.getDateTime() + "."));

        if (reservation.getStatus() == Reservation.Status.CANCELADO) {
            throw new IllegalArgumentException("Reserva já foi cancelada.");
        }

        reservation.setStatus(Reservation.Status.CANCELADO);
        Reservation cancelledReservation = reservationRepository.save(reservation);

        RestaurantTable table = cancelledReservation.getTable();
        if (table != null) {
            table.setStatus(RestaurantTable.TableStatus.DISPONIVEL);
            tableRepository.save(table);
        }
        return new ReservationResponseDTO(cancelledReservation);
    }


}
