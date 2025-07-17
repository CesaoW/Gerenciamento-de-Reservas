package desafio.programacao.ReservaRestaurante.service;

import desafio.programacao.ReservaRestaurante.dto.RestTableDTO.*;
import desafio.programacao.ReservaRestaurante.model.RestaurantTable;
import desafio.programacao.ReservaRestaurante.repository.RestTableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class RestTableService {
    private RestTableRepository tableRepository;

    public RestTableService(RestTableRepository tableRepository){
        this.tableRepository = tableRepository;
    }

    public RestTableResponseDTO registerTable(RestTableRegisterDTO tableRegisterDTO) {
        if(tableRegisterDTO.getTableNumber() <= 0){
            throw new IllegalArgumentException("Número da mesa deve ser uma valor inteiro positivo!");
        }
        //valida a capacidade da mesa
        if (tableRegisterDTO.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacidade da mesa deve ser maior que zero.");
        }
        //verifica se já existe a mesa com mesmo numero
        Optional<RestaurantTable> existingTable = tableRepository.findByTableNumber(tableRegisterDTO.getTableNumber());
        if(existingTable.isPresent()){
            throw new IllegalArgumentException("Já existe uma mesa com esse número");
        }
        RestaurantTable newTableEntity = new RestaurantTable(tableRegisterDTO.getTableNumber(), tableRegisterDTO.getCapacity(), tableRegisterDTO.getStatus());
        RestaurantTable savedTable = tableRepository.save(newTableEntity);
        return new RestTableResponseDTO(savedTable);
    }

    //metodo para listar as mesas existentes
    public List<RestTableResponseDTO> getAllTables(){
        return tableRepository.findAll()
                .stream()
                .map(RestTableResponseDTO::new)
                .collect(Collectors.toList());
    }

    public RestTableResponseDTO updateTable(Long id, RestTableUpdateDTO tableUpdateDTO){
        RestaurantTable toUpdate = tableRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Mesar nao encontrada com ID: "+ id));

        if (tableUpdateDTO.getTableNumber() > 0) {
            toUpdate.setTableNumber(tableUpdateDTO.getTableNumber());
        }
        if (tableUpdateDTO.getCapacity() > 0) {
            toUpdate.setCapacity(tableUpdateDTO.getCapacity());
        }
        if (tableUpdateDTO.getStatus() != null) {
            toUpdate.setStatus(tableUpdateDTO.getStatus());
        }

        RestaurantTable updatedTable = tableRepository.save(toUpdate);
        return  new RestTableResponseDTO(updatedTable);

    }


    //metodo para deletar a mesa, por ID
    public void deleteTable(Long id){
        if(!tableRepository.existsById(id)){
            throw new EntityNotFoundException("Mesa nao encontrada com ID:"+id);
        }
        tableRepository.findById(id);
    }

}
