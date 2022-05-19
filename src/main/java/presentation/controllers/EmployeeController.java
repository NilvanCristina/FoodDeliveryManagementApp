package presentation.controllers;

import business.DeliveryService;
import presentation.EmployeeGUI;
import presentation.MainGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController implements ActionListener {
    private EmployeeGUI employeeGUI;
    private DeliveryService deliveryService;

    public EmployeeController(EmployeeGUI employeeGUI, DeliveryService deliveryService) {
        this.employeeGUI = employeeGUI;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        employeeGUI.closeWindow();
        MainGUI mainGUI = new MainGUI(deliveryService);
    }
}
