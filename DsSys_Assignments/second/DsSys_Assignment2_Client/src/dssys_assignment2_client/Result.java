/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dssys_assignment2_client;


public class Result {

    private final String destination;    
    private final int price;
    
    public Result(String destination, int price){
        this.destination = destination;
        this.price = price;
    }
    
    @Override
    public String toString(){
        return "Destination: " + destination + "\nCheapest Flight: " + price;
    }
}
