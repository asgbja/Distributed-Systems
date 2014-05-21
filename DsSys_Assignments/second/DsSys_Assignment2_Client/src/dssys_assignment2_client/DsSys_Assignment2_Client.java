package dssys_assignment2_client;

import TXLFlightService.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class DsSys_Assignment2_Client {

    private static ClientGUI client;
    private static JButton textButton;
    private static String destination = "";

    public static void main(String[] args) {

        
        initializeGUI();

    }

    private static void initializeGUI() {
        client = new ClientGUI();
        client.setVisible(true);

        textButton = client.getButton();
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destination = client.getDestinationField().getText();
                doTheRest();
            }
        });

    }

    private static void doTheRest() {
        System.out.println(destination);
        TXLFlightPort port = new TXLFlight().getTXLFlightBinding();  
        int price = port.txlFlightcalc(destination);
        Result result = new Result(destination, price);
        client.getTextField().setText(result.toString());
    }
}
