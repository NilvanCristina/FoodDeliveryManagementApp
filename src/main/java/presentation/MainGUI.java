package presentation;

import presentation.controllers.MainController;
import utils.PresentationFunctions;
import business.DeliveryService;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends Component {
    private JFrame frame = new JFrame("Log In Window");
    private JTextField usernameField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private JComboBox<String> rolesComboBox = new JComboBox<>(new String[] {"administrator", "employee", "client"});
    private JButton logInButton = new JButton("Log In");
    private JButton registerButton = new JButton("Register");
    private JButton closeButton = new JButton("Close");

    private DeliveryService deliveryService;
    private MainController mainController;
    private PresentationFunctions presentationFunctions = new PresentationFunctions();

    public MainGUI(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.mainController = new MainController(this, deliveryService);

        createMainGUI();
    }

    private void createMainGUI() {
        resetFields();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 600);

        logInButton.addActionListener(mainController);
        registerButton.addActionListener(mainController);
        closeButton.addActionListener(mainController);

        JPanel usernamePanel = presentationFunctions.getPanelWithTextField(usernameField, "Username");
        JPanel passwordPanel = presentationFunctions.getPanelWithTextField(passwordField, "Password");
        JPanel comboBoxPanel = presentationFunctions.getPanelWithComboBox(rolesComboBox, "Role");
        JPanel logButtonPanel = presentationFunctions.getPanelWithButton(logInButton);
        JPanel registerButtonPanel = presentationFunctions.getPanelWithButton(registerButton);
        JPanel closeButtonPanel = presentationFunctions.getPanelWithButton(closeButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(comboBoxPanel);
        mainPanel.add(logButtonPanel);
        mainPanel.add(registerButtonPanel);
        mainPanel.add(closeButtonPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public JComboBox<String> getFromComboBox() {
        return rolesComboBox;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void closeWindow() {
        frame.dispose();
    }
}
