package dssys_assignment2_client;

import TXLFlightService.*;
import hotelHilton.HotelHilton;
import hotelHilton.HotelHiltonPort;
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

    private static void callWebServices(String destination) {
        TXLFlightPort port = new TXLFlight().getTXLFlightBinding();  
        int price = port.txlFlightcalc(destination);
        HotelHiltonPort hiltonPort = new HotelHilton().getHotelHiltonBinding();
        int hiltonPrice = hiltonPort.hotelHiltoncalc(destination);
        System.out.println(hiltonPrice);
        Result result = new Result(destination, price, hiltonPrice);
        client.getTextField().setText(result.toString());
    }
}
