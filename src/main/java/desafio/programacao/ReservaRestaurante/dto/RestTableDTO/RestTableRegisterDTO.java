package desafio.programacao.ReservaRestaurante.dto.RestTableDTO;

import desafio.programacao.ReservaRestaurante.model.RestaurantTable;

public class RestTableRegisterDTO {
    private Integer tableNumber;
    private Integer capacity;
    private RestaurantTable.TableStatus status;

    //Construtores
    public RestTableRegisterDTO(){};
    public RestTableRegisterDTO(Integer tableNumber, Integer capacity, RestaurantTable.TableStatus status){
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
    }

    //Getters
    public Integer getTableNumber(){return tableNumber;}
    public Integer getCapacity(){return capacity;}
    public RestaurantTable.TableStatus getStatus(){return status;}

    //Setters
    public void setTableNumber(int tableNumber){this.tableNumber = tableNumber;}
    public void setCapacity(int capacity){this.capacity = capacity;}
    public void setStatus(RestaurantTable.TableStatus status){this.status = status;}
}
