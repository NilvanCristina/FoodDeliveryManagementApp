package presentation;

import presentation.controllers.EmployeeController;
import utils.Notification;
import utils.PresentationFunctions;
import business.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI extends Component implements Observer {
    private JFrame frame = new JFrame("Employee Window");
    private JTextArea resultTextArea = new JTextArea();
    private JButton logOutButton = new JButton("Log out");

    private DeliveryService deliveryService;
    private EmployeeController employeeController;
    private PresentationFunctions presentationFunctions = new PresentationFunctions();

    public EmployeeGUI(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        employeeController = new EmployeeController(this, deliveryService);
    }

    public void createEmployeeGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 600);

        logOutButton.addActionListener(employeeController);

        setResultTextArea(Notification.readMessage());
        JScrollPane resultPanel = presentationFunctions.getTextAreaPanel(resultTextArea);
        JPanel logOutPanel = presentationFunctions.getPanelWithButton(logOutButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(resultPanel);
        mainPanel.add(logOutPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void setResultTextArea(String resultText) {
        resultTextArea.setText(resultText);
    }

    public void closeWindow() {
        frame.dispose();
    }

    @Override
    public void update(Observable observable, Object object) {
        Notification.writeMessage(object.toString());
    }
}
