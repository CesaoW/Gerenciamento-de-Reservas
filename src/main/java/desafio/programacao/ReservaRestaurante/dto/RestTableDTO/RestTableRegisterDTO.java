package desafio.programacao.ReservaRestaurante.dto.RestTableDTO;

import desafio.programacao.ReservaRestaurante.model.RestaurantTable;

public class RestTableRegisterDTO {
    private long id;
    private int tableNumber;
    private int capacity;
    private String status;

    //Construtores
    public RestTableRegisterDTO(){};
    public RestTableRegisterDTO(int tableNumber, int capacity, String status){
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
    }

    //Getters
    public int getTableNumber(){return tableNumber;}
    public int getCapacity(){return capacity;}
    public String getStatus(){return status;}

    //Setters
    public void setTableNumber(int tableNumber){this.tableNumber = tableNumber;}
    public void setCapacity(int capacity){this.capacity = capacity;}
    public void setStatus(String status){this.status = status;}
}
