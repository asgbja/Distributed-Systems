package flightHotelCarServices;

import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "HotelHiltonPort", portName = "HotelHiltonBinding",
        serviceName = "HotelHilton", targetNamespace = "HotelHilton")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HotelHiltonService {

    private final HashMap<String, Integer> pricesForDestinantion;

    public HotelHiltonService() {
        pricesForDestinantion = new HashMap<>();
        pricesForDestinantion.put("FRA", 745);
        pricesForDestinantion.put("MUC", 450);
        pricesForDestinantion.put("STR", 340);
    }

    @WebMethod(operationName = "HotelHiltoncalc")
    @WebResult(name = "HotelHiltonResponse")
    public int HotelHiltonPrice(@WebParam(name = "GCDRequest_paramA") String paramA) {
        return pricesForDestinantion.get(paramA);
    }
}
