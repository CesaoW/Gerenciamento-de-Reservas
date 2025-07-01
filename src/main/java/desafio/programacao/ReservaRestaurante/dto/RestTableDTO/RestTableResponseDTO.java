package desafio.programacao.ReservaRestaurante.dto.RestTableDTO;

import desafio.programacao.ReservaRestaurante.model.RestaurantTable;

public class RestTableResponseDTO {
    private long id;
    private long tableNumber;
    private int capacity;
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
    public long getTableNumber() {return tableNumber;}
    public int getCapacity() {return capacity;}
    public RestaurantTable.TableStatus getStatus() {return status;}

    //Setters
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public void setTableNumber(long tableNumber) {this.tableNumber = tableNumber;}
    public void setStatus(RestaurantTable.TableStatus status) {this.status = status;}

}
