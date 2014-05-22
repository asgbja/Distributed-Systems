
package dssys_assignment2_server;

import javax.xml.ws.Endpoint;

public class DsSys_Assignment2_Server {


    public static void main(String[] args) {
        //Endpoint ep = 
        Endpoint.publish("http://localhost:8181/TXLFlightService", new TXLFlightService());
        Endpoint.publish("http://localhost:8181/HotelHiltonService", new HotelHiltonService());
        Endpoint.publish("http://localhost:8181/HotelHolidayInnService", new HotelHolidayInnService());
        Endpoint.publish("http://localhost:8181/HotelMercureService", new HotelMercureService());
        Endpoint.publish("http://localhost:8181/CarSixtService", new CarSixtService());
      //  JOptionPane.showMessageDialog( null, "Stop server" );
      //  ep.stop();
    }
    
}
