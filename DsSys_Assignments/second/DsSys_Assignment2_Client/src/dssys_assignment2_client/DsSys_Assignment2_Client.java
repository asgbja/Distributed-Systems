
package dssys_assignment2_client;

import TXLFlightService.*;

public class DsSys_Assignment2_Client {
    
    public static void main(String[] args) {
        TXLFlightPort port = new TXLFlight().getTXLFlightBinding();
        
        int price = port.txlFlightcalc("MUC");
        System.out.println(price);
    }
    
}
