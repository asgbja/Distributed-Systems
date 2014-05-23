
package hotelCar;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "HotelCarPort", portName = "HotelCarBinding",
        serviceName = "HotelCar", targetNamespace = "HotelCar")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HotelCarService {
    
    @WebMethod(operationName = "HotelCarcalc")
    @WebResult(name = "HotelCarResponse")
    public String HotelCarPrice(@WebParam(name = "GCDRequest_paramA") String paramA) {        
        return (getBestCarPrice(paramA) + getBestHotelPrice(paramA));
    }
        
    private String getBestCarPrice(String destination){
        int carSixt = getPriceOfCarSixt(destination);
        int europCar = getPriceOfEuropCar(destination);
        
        if(carSixt==-1 && europCar==-1){
            return "\n\nNo car for this destination";
        }else if(carSixt==-1 && europCar!=-1){
            return "\n\nEurop Car for: " + europCar + "€";
        }else if(europCar==-1 && carSixt!=-1){
            return "\n\nECarSixt for: " + carSixt + "€";
        }else if(europCar < carSixt){
            return "\n\nEurop Car for: " + europCar + "€";
        }
        return "\n\nECarSixt for: " + carSixt + "€";
    }
    
    private String getBestHotelPrice(String destination) {
        
        int hiltonPrice = getPriceOfHilton(destination);
        int holidayInnPrice = getPriceOfHolidayInn(destination);
        int mercurePrice = getPriceOfMercure(destination);
        
        if(hiltonPrice!=-1 && hiltonPrice <= holidayInnPrice && hiltonPrice <= mercurePrice)
            return "\n\nHilton Hotel for " + hiltonPrice + " €";
        if(holidayInnPrice!=-1 && holidayInnPrice<=hiltonPrice && holidayInnPrice <= mercurePrice)
            return "\n\nHotel Inn for " + holidayInnPrice + " €";
        if(mercurePrice!=-1 && mercurePrice<=hiltonPrice && mercurePrice<=holidayInnPrice)
            return "\n\nMercure Hotel for " + mercurePrice + " €";
        return "No hotel found";
    }
    
    
    private int getPriceOfCarSixt(String destination){
        CarSixtPort carSixtPort = new CarSixt().getCarSixtBinding();
        return carSixtPort.carSixtcalc(destination);
    }
    
    private int getPriceOfEuropCar(String destination){
        CarEuropCarPort port = new CarEuropCar().getCarEuropCarBinding();
        return port.carEuropCarcalc(destination);
    }
    
    private int getPriceOfHilton(String destination){
        HotelHiltonPort hiltonPort = new HotelHilton().getHotelHiltonBinding();
        return hiltonPort.hotelHiltoncalc(destination);
    }
    
    private int getPriceOfHolidayInn(String destination){
        HotelHolidayInnPort holidayInnPort = new HotelHolidayInn().getHotelHolidayInnBinding();
        return holidayInnPort.hotelHolidayInncalc(destination);
    }
    
    private int getPriceOfMercure(String destination) {
        HotelMercurePort mercurePort = new  HotelMercure().getHotelMercureBinding();
        return mercurePort.hotelMercurecalc(destination);
    }
}
