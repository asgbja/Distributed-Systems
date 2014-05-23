package flightHotelCarServices;

import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "HotelHolidayInnPort", portName = "HotelHolidayInnBinding",
        serviceName = "HotelHolidayInn", targetNamespace = "HotelHolidayInn")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HotelHolidayInnService {

    private final HashMap<String, Integer> pricesForDestinantion;

    public HotelHolidayInnService() {
        pricesForDestinantion = new HashMap<>();
        pricesForDestinantion.put("FRA", 1000);
        pricesForDestinantion.put("MUC", 54);
        pricesForDestinantion.put("STR", 340);
    }

    @WebMethod(operationName = "HotelHolidayInncalc")
    @WebResult(name = "HotelHolidayInn")
    public int HotelHiltonPrice(@WebParam(name = "GCDRequest_paramA") String paramA) {
        return pricesForDestinantion.get(paramA);
    }

}
