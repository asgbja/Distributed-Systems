package dssys_assignment2_client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import webService.*;

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
        client.getTextField().setText(getAnswerFromServers(destination));
    }
    
    private String getAnswerFromServers(String destination){
        FlightHotelCarPort port = new FlightHotelCar().getFlightHotelCarBinding();
        return port.flightHotelCarcalc(destination);
    }
    
}
