
package flightHotelCar;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "FlightHotelCarPort", portName = "FlightHotelCarBinding",
        serviceName = "FlightHotelCar", targetNamespace = "FlightHotelCar")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class FlightHotelCarService {
    

    @WebMethod(operationName = "FlightHotelCarcalc")
    @WebResult(name = "FlightHotelCar")
    public String FlightHotelCarService(@WebParam(name = "GCDRequest_paramA") String destination) {
        return getClientResponse(destination);
    }
    
    private String getClientResponse(String destination){
        int flightPrice = getPriceOfFlight(destination);
        if(flightPrice==-1){
            return "No flights available for " + destination;
        }
        return "Destination: " + destination + "\n\nCheapest Flight for: " + flightPrice + "€"
                getHotelAndCAr(destination);
    }
    
    private int getPriceOfFlight(String destination){
        TXLFlightPort port = new TXLFlight().getTXLFlightBinding();  
        return port.txlFlightcalc(destination);
    }
    
    private String getHotelAndCAr(String destination){
        HotelCarPort port = new HotelCar().getHotelCarBinding();
        return port.hotelCarcalc(destination);
    }
}
