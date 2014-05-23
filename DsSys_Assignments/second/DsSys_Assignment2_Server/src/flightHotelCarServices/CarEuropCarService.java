package flightHotelCarServices;

import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "CarEuropCarPort", portName = "CarEuropCarBinding",
        serviceName = "CarEuropCar", targetNamespace = "CarEuropCar")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CarEuropCarService {

    private final HashMap<String, Integer> pricesForDestinantion;

    public CarEuropCarService() {
        pricesForDestinantion = new HashMap<>();
        pricesForDestinantion.put("FRA", -1);
        pricesForDestinantion.put("MUC", -1);
        pricesForDestinantion.put("STR", 70);
    }

    @WebMethod(operationName = "CarEuropCarcalc")
    @WebResult(name = "CarEuropCarResponse")
    public int CarSixtService(@WebParam(name = "GCDRequest_paramA") String paramA) {
        return pricesForDestinantion.get(paramA);
    }

}
