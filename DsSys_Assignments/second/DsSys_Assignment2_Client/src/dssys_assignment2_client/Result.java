/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dssys_assignment2_client;


public class Result {

    private final String destination;    
    private final int price;
    private final int hiltonPrice;
    
    public Result(String destination, int price, int hiltonPrice){
        this.destination = destination;
        this.price = price;
        this.hiltonPrice = hiltonPrice;
    }
    
    @Override
    public String toString(){
        return "Destination: " + destination + "\n\nCheapest Flight: " + price +
                "\n\nPrice in Hilton Hotel: " + hiltonPrice;
    }
}
