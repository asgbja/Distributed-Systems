
package flightHotelCarServices;

import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="TXLFlightPort", portName = "TXLFlightBinding",
serviceName="TXLFlight", targetNamespace="TXLFlight")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TXLFlightService {
    
    private final HashMap<String, Integer> pricesForDestinantion;
    
    public TXLFlightService(){
        pricesForDestinantion = new HashMap<>();
        pricesForDestinantion.put("FRA", 120);
        pricesForDestinantion.put("MUC", 89);
        pricesForDestinantion.put("STR", -1);
    }
    
    @WebMethod(operationName = "TXLFlightcalc")
    @WebResult(name = "TXLFlightResponse")
    public int TXLFlightPrice(@WebParam(name="GCDRequest_paramA")String paramA){
        return pricesForDestinantion.get(paramA);
    }
}
