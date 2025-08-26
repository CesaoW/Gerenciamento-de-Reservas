package desafio.programacao.ReservaRestaurante.controller;


import desafio.programacao.ReservaRestaurante.dto.RestTableDTO.*;
import desafio.programacao.ReservaRestaurante.service.RestTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
@Tag(name = "Mesas", description = "Gerenciador de Mesas")
public class RestTableController {
    private final RestTableService tableService;

    public RestTableController(RestTableService tableService){
        this.tableService  = tableService;
    }

    @PostMapping()
    @Operation(summary = "Cadastro de mesas", description = "Cadastra uma nova mesa na base dados, apenas administradores podem criar novas mesas")
    @SecurityRequirement(name= "bearer-key")
    public ResponseEntity<?> registerTable(@RequestBody RestTableRegisterDTO tableRegisterDTO){
        try{
            RestTableResponseDTO newTable = tableService.registerTable(tableRegisterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTable);
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @Operation(summary = "Lista de mesas", description = "Retorna uma lista com todas as mesas exitentes na base de dados")
    public ResponseEntity<java.util.List<RestTableResponseDTO>> getAllTables() {
        List<RestTableResponseDTO> allTables = tableService.getAllTables();
        return ResponseEntity.ok(allTables);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Altera dados de mesas", description = "Apenas administradores podem alterar dados de mesas pelo seu ID")
    @SecurityRequirement(name= "bearer-key")
     ResponseEntity<RestTableResponseDTO> updateTable(@PathVariable Long id, @RequestBody RestTableUpdateDTO tableUpdateDTO){
        try{
            RestTableResponseDTO toUpdate = tableService.updateTable(id, tableUpdateDTO);
            return ResponseEntity.ok(toUpdate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de uma mesas", description = "Apenas administradores podem excluir mesas da base de dados pelo seu ID")
    @SecurityRequirement(name= "bearer-key")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id){
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
