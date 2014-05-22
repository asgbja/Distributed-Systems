package dssys_assignment2_client;

import TXLFlightService.*;
import hotelHilton.*;
import hotelHolidayInn.*;
import hotelMercure.*;
import carSixt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class DsSys_Assignment2_Client {

    private static ClientGUI client;

    public static void main(String[] args) {
        new DsSys_Assignment2_Client().initializeClient();
    }

    private void initializeClient() {
        client = new ClientGUI();
        client.setVisible(true);

        JButton textButton = client.getButton();
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destination = client.getDestinationField().getText();
                callWebServices(destination);
            }
        });

    }

    private void callWebServices(String destination) {    
        int price = getPriceOfFlight(destination);
        int hiltonPrice = getPriceOfHilton(destination);
        int holidayInnPrice = getPriceOfHolidayInn(destination);
        int mercurePrice = getPriceOfMercure(destination);
        int carSixtPrice = getPriceOfCarSixt(destination);
        Result result = new Result(destination, price, hiltonPrice, holidayInnPrice,
        mercurePrice, carSixtPrice);
        client.getTextField().setText(result.toString());
    }
    
    private int getPriceOfFlight(String destination){
        TXLFlightPort port = new TXLFlight().getTXLFlightBinding();  
        return port.txlFlightcalc(destination);
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
    
    private int getPriceOfCarSixt(String destination){
        CarSixtPort carSixtPort = new CarSixt().getCarSixtBinding();
        return carSixtPort.carSixtcalc(destination);
    }
}
