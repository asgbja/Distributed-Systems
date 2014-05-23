package flightHotelCarServices;

import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "HotelMercurePort", portName = "HotelMercureBinding",
        serviceName = "HotelMercure", targetNamespace = "HotelMercure")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HotelMercureService {

    private final HashMap<String, Integer> pricesForDestinantion;

    public HotelMercureService() {
        pricesForDestinantion = new HashMap<>();
        pricesForDestinantion.put("FRA", 120);
        pricesForDestinantion.put("MUC", 130);
        pricesForDestinantion.put("STR", 760);
    }

    @WebMethod(operationName = "HotelMercurecalc")
    @WebResult(name = "HotelMercureResponse")
    public int HotelMercurePrice(@WebParam(name = "GCDRequest_paramA") String paramA) {
        return pricesForDestinantion.get(paramA);
    }
    
}
