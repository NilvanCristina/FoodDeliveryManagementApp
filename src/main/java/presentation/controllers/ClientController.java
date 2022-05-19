package presentation.controllers;

import business.DeliveryService;
import data.Serializator;
import data.User;
import presentation.ClientGUI;
import presentation.MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController implements ActionListener {
    private ClientGUI clientGUI;
    private DeliveryService deliveryService;
    private User client;

    public ClientController(ClientGUI clientGUI, DeliveryService deliveryService, User client) {
        this.clientGUI = clientGUI;
        this.deliveryService = deliveryService;
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if (button.getSource() == clientGUI.getMenuButton()) {
            String stringMenu = deliveryService.getStringMenu();
            clientGUI.resetTextArea();
            clientGUI.setResultTextArea(stringMenu);
        }

        if (button.getSource() == clientGUI.getSearchButton()) {
            String criteria = String.valueOf(clientGUI.getCriteriaComboBox().getSelectedItem());
            String value = clientGUI.getSearchValueField();
            String items = deliveryService.searchByCriteria(criteria, value);

            clientGUI.resetTextArea();
            clientGUI.setResultTextArea(items);
        }

        if (button.getSource() == clientGUI.getAddItemButton()) {
            String menuItemTitle = String.valueOf(clientGUI.getMenuComboBox().getSelectedItem());
            deliveryService.addToList(menuItemTitle, "cart");
        }

        if (button.getSource() == clientGUI.getShowCartButton()) {
            String shoppingCart = deliveryService.getListContent("cart");
            clientGUI.resetTextArea();
            clientGUI.setResultTextArea(shoppingCart);
        }

        if (button.getSource() == clientGUI.getOrderButton()) {
            String clientUsername = client.getUsername();
            deliveryService.createOrder(clientUsername);
            deliveryService.emptyList("cart");

            String message = "Your order is being prepared!";
            JOptionPane.showMessageDialog(clientGUI, message, "Confirm order", JOptionPane.INFORMATION_MESSAGE);
        }

        if (button.getSource() == clientGUI.getLogOutButton()) {
            deliveryService.emptyList("cart");
            deliveryService.emptyList("composite");

            clientGUI.closeWindow();

            MainGUI mainGUI = new MainGUI(deliveryService);
            Serializator.serialize(deliveryService);
        }
    }
}
