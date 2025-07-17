package desafio.programacao.ReservaRestaurante.controller;


import desafio.programacao.ReservaRestaurante.dto.RestTableDTO.*;
import desafio.programacao.ReservaRestaurante.service.RestTableService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class RestTableController {
    private final RestTableService tableService;

    public RestTableController(RestTableService tableService){
        this.tableService  = tableService;
    }

    @PostMapping()
    public ResponseEntity<?> registerTable(@RequestBody RestTableRegisterDTO tableRegisterDTO){
        try{
            RestTableResponseDTO newTable = tableService.registerTable(tableRegisterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTable);
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<java.util.List<RestTableResponseDTO>> getAllTables() {
        List<RestTableResponseDTO> allTables = tableService.getAllTables();
        return ResponseEntity.ok(allTables);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestTableResponseDTO> updateTable(@PathVariable Long id, @RequestBody RestTableUpdateDTO tableUpdateDTO){
        try{
            RestTableResponseDTO toUpdate = tableService.updateTable(id, tableUpdateDTO);
            return ResponseEntity.ok(toUpdate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id){
        try{
            tableService.deleteTable(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
