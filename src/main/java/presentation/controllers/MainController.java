package presentation.controllers;

import business.DeliveryService;
import data.Serializator;
import data.User;
import presentation.AdministratorGUI;
import presentation.ClientGUI;
import presentation.EmployeeGUI;
import presentation.MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private MainGUI mainGUI;
    private EmployeeGUI employeeGUI;
    private DeliveryService deliveryService;

    public MainController(MainGUI mainGUI, DeliveryService deliveryService) {
        this.mainGUI = mainGUI;
        this.employeeGUI = new EmployeeGUI(deliveryService);
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if (button.getSource() == mainGUI.getLogInButton()) {
            String username = mainGUI.getUsernameField();
            String password = mainGUI.getPasswordField();
            String role = String.valueOf(mainGUI.getFromComboBox().getSelectedItem());
            String message = deliveryService.getSearchUserMessage(username, password, role);

            if (message.equals("WRONG_PASSWORD") || message.equals("WRONG_USERNAME") || message.equals("WRONG_ROLE")) {
                JOptionPane.showMessageDialog(mainGUI, message, "Error Message", JOptionPane.ERROR_MESSAGE);
            } else if (role.equals("client")) {
                mainGUI.closeWindow();

                deliveryService.addObserver(employeeGUI);
                User client = deliveryService.getUser(username);
                ClientGUI clientGUI = new ClientGUI(deliveryService, client);
            } else if (role.equals("administrator")) {
                mainGUI.closeWindow();

                User administrator = deliveryService.getUser(username);
                AdministratorGUI administratorGUI = new AdministratorGUI(deliveryService, administrator);
            } else if (role.equals("employee")) {
                mainGUI.closeWindow();
                employeeGUI.createEmployeeGUI();
            }
        }

        if (button.getSource() == mainGUI.getRegisterButton()) {
            String username = mainGUI.getUsernameField();
            String password = mainGUI.getPasswordField();
            String role = String.valueOf(mainGUI.getFromComboBox().getSelectedItem());

            deliveryService.addUser(username, password, role);
            Serializator.serialize(deliveryService);
        }

        if (button.getSource() == mainGUI.getCloseButton()) {
            mainGUI.closeWindow();
            Serializator.serialize(deliveryService);
        }
    }
}
