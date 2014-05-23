
package dssys_assignment2_server;

import flightHotelCarServices.*;
import flightHotelCar.FlightHotelCarService;
import hotelCar.HotelCarService;

import javax.xml.ws.Endpoint;

public class DsSys_Assignment2_Server {


    public static void main(String[] args) {
        
        Endpoint.publish("http://localhost:8181/FlightHotelCarService", new FlightHotelCarService());
        Endpoint.publish("http://localhost:8181/HotelCarService", new HotelCarService());
        
        //All the concrete services
        Endpoint.publish("http://localhost:8181/TXLFlightService", new TXLFlightService());
        Endpoint.publish("http://localhost:8181/HotelHiltonService", new HotelHiltonService());
        Endpoint.publish("http://localhost:8181/HotelHolidayInnService", new HotelHolidayInnService());
        Endpoint.publish("http://localhost:8181/HotelMercureService", new HotelMercureService());
        Endpoint.publish("http://localhost:8181/CarSixtService", new CarSixtService());
        Endpoint.publish("http://localhost:8181/CarEuropCarService", new CarEuropCarService());
        
      //  JOptionPane.showMessageDialog( null, "Stop server" );
      //  ep.stop();
    }
    
}
