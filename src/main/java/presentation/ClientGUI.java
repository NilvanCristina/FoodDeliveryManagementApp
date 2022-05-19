package presentation;

import presentation.controllers.ClientController;
import utils.PresentationFunctions;
import business.DeliveryService;
import data.User;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends Component {
    private JFrame frame = new JFrame("Client Window");
    private JButton menuButton = new JButton("Menu");
    private JButton searchButton = new JButton("Search By");
    private JComboBox<String> criteriaComboBox = new JComboBox<>(new String[] {"title", "rating", "calories", "protein",
                                                                            "fat", "sodium", "price"});
    private JTextField searchValueField = new JTextField(10);
    private JButton addItemButton = new JButton("Add");
    private JComboBox<String> menuComboBox;
    private JButton orderButton = new JButton("Order");
    private JButton showCartButton = new JButton("Shopping cart");
    private JTextArea resultTextArea = new JTextArea();
    private JButton logOutButton = new JButton("Log out");

    private DeliveryService deliveryService;
    private ClientController clientController;
    private PresentationFunctions presentationFunctions = new PresentationFunctions();

    public ClientGUI(DeliveryService deliveryService, User client) {
        this.deliveryService = deliveryService;
        clientController = new ClientController(this, deliveryService, client);
        menuComboBox = new JComboBox<>(deliveryService.getMenuTitles());

        createClientGUI();
    }

    private void createClientGUI() {
        resetFields();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 600);

        menuButton.addActionListener(clientController);
        searchButton.addActionListener(clientController);
        addItemButton.addActionListener(clientController);
        orderButton.addActionListener(clientController);
        showCartButton.addActionListener(clientController);
        logOutButton.addActionListener(clientController);

        JPanel menuPanel = presentationFunctions.getPanelWithButton(menuButton, "Click here to see the menu: ");
        JPanel findPanel = presentationFunctions.getPanelWithComboBox(criteriaComboBox, searchButton, searchValueField);
        JPanel orderPanel = presentationFunctions.getPanelWithComboBoxAndButtons(menuComboBox, orderButton, addItemButton);
        JPanel cartPanel = presentationFunctions.getPanelWithButton(showCartButton);
        JScrollPane resultPanel = presentationFunctions.getTextAreaPanel(resultTextArea);
        JPanel logOutPanel = presentationFunctions.getPanelWithButton(logOutButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(menuPanel);
        mainPanel.add(findPanel);
        mainPanel.add(orderPanel);
        mainPanel.add(cartPanel);
        mainPanel.add(resultPanel);
        mainPanel.add(logOutPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void resetFields() {
        searchValueField.setText("");
        resultTextArea.setText("");
    }

    public void resetTextArea() {
        resultTextArea.setText("");
    }

    public String getSearchValueField() {
        return searchValueField.getText();
    }

    public void setResultTextArea(String resultText) {
        resultTextArea.setText(resultText);
    }

    public JComboBox<String> getCriteriaComboBox() {
        return criteriaComboBox;
    }

    public JComboBox<String> getMenuComboBox() {
        return menuComboBox;
    }

    public JButton getMenuButton() {
        return menuButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getAddItemButton() {
        return addItemButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public JButton getShowCartButton() {
        return showCartButton;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public void closeWindow() {
        frame.dispose();
    }
}
