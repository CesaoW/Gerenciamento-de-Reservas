package desafio.programacao.ReservaRestaurante.dto.RestTableDTO;

import desafio.programacao.ReservaRestaurante.model.RestaurantTable;

public class RestTableResponseDTO {
    private Long id;
    private Integer tableNumber;
    private Integer capacity;
    private RestaurantTable.TableStatus status;

    // construtores
    public RestTableResponseDTO() {};
    public RestTableResponseDTO(RestaurantTable restaurantTable){
        this.id = restaurantTable.getTableId();
        this.tableNumber = restaurantTable.getTableNumber();
        this.capacity = restaurantTable.getCapacity();
        this.status = restaurantTable.getStatus();
    }

    //Getters

    public Long getId() {return id;}
    public Integer getTableNumber() {return tableNumber;}
    public Integer getCapacity() {return capacity;}
    public RestaurantTable.TableStatus getStatus() {return status;}

    //Setters
    public void setCapacity(Integer capacity) {this.capacity = capacity;}
    public void setTableNumber(Integer tableNumber) {this.tableNumber = tableNumber;}
    public void setStatus(RestaurantTable.TableStatus status) {this.status = status;}

}
