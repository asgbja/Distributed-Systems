package flightHotelCarServices;

import java.util.HashMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "CarSixtPort", portName = "CarSixtBinding",
        serviceName = "CarSixt", targetNamespace = "CarSixt")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CarSixtService {

    private final HashMap<String, Integer> pricesForDestinantion;

    public CarSixtService() {
        pricesForDestinantion = new HashMap<>();
        pricesForDestinantion.put("FRA", -1);
        pricesForDestinantion.put("MUC", 35);
        pricesForDestinantion.put("STR", 60);
    }

    @WebMethod(operationName = "CarSixtcalc")
    @WebResult(name = "CarSixtResponse")
    public int CarSixtService(@WebParam(name = "GCDRequest_paramA") String paramA) {
        return pricesForDestinantion.get(paramA);
    }

}
