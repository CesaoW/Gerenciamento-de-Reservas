package desafio.programacao.ReservaRestaurante.dto.RestTableDTO;

import desafio.programacao.ReservaRestaurante.model.RestaurantTable;

public class RestTableUpdateDTO {
    private int tableNumber;
    private int capacity;
    private RestaurantTable.TableStatus status;

    // construtores
    public RestTableUpdateDTO() {};
    public RestTableUpdateDTO(RestaurantTable restaurantTable){
        this.tableNumber = restaurantTable.getTableNumber();
        this.capacity = restaurantTable.getCapacity();
        this.status = restaurantTable.getStatus();
    }

    //Getters
    public int getTableNumber() {return tableNumber;}
    public int getCapacity() {return capacity;}
    public RestaurantTable.TableStatus getStatus() {return status;}

    //Setters
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public void setTableNumber(int tableNumber) {this.tableNumber = tableNumber;}
    public void setStatus(RestaurantTable.TableStatus status) {this.status = status;}
}
