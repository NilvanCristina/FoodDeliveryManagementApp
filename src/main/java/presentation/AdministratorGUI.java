package presentation;

import presentation.controllers.AdministratorController;
import utils.PresentationFunctions;
import business.DeliveryService;
import data.User;

import javax.swing.*;
import java.awt.*;

public class AdministratorGUI extends Component {
    private JFrame frame = new JFrame("Administrator Window");
    private JButton importButton = new JButton("Import");
    private JButton insertButton = new JButton("Insert");
    private JTextField titleField = new JTextField(10);
    private JTextField ratingField = new JTextField(4);
    private JTextField caloriesField = new JTextField(4);
    private JTextField proteinField = new JTextField(4);
    private JTextField fatField = new JTextField(4);
    private JTextField sodiumField = new JTextField(4);
    private JTextField priceField = new JTextField(6);
    private JButton updateButton = new JButton("Update");
    private JComboBox<String> toSetComboBox = new JComboBox<>(new String[] {"title", "rating", "calories", "protein",
                                                                        "fat", "sodium", "price"});
    private JTextField toSetValueField = new JTextField(6);
    private JComboBox<String> conditionComboBox = new JComboBox<>(new String[] {"title", "rating", "calories", "protein",
                                                                            "fat", "sodium", "price"});
    private JTextField conditionValueField = new JTextField(6);
    private JButton deleteButton = new JButton("Delete");
    private JComboBox<String> toDeleteComboBox;
    private JButton createButton = new JButton("Create");
    private JTextField createField = new JTextField(10);
    private JComboBox<String> menuComboBox;
    private JButton addButton = new JButton("Add");
    private JButton showSelectedButton = new JButton("Selected items");
    private JButton reportButton = new JButton("Report");
    private JComboBox<String> reportComboBox = new JComboBox<>(new String[] {"Time Interval Report",
                                                                            "Ordered More Than Report",
                                                                            "Clients Ordered More Than Report",
                                                                            "Ordered In Day Report"});
    private JTextField reportField = new JTextField(10);
    private JButton infoButton = new JButton("Report Info");
    private JTextArea resultTextArea = new JTextArea();
    private JButton logOutButton = new JButton("Log out");

    private DeliveryService deliveryService;
    private AdministratorController administratorController;
    private PresentationFunctions presentationFunctions = new PresentationFunctions();

    public AdministratorGUI(DeliveryService deliveryService, User administrator) {
        this.deliveryService = deliveryService;
        administratorController = new AdministratorController(this, deliveryService, administrator);
        menuComboBox = new JComboBox<>(deliveryService.getMenuTitles());
        toDeleteComboBox = new JComboBox<>(deliveryService.getMenuTitles());

        createAdministratorGUI();
    }

    public void createAdministratorGUI() {
        resetFields();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 600);

        importButton.addActionListener(administratorController);
        insertButton.addActionListener(administratorController);
        updateButton.addActionListener(administratorController);
        deleteButton.addActionListener(administratorController);
        createButton.addActionListener(administratorController);
        addButton.addActionListener(administratorController);
        reportButton.addActionListener(administratorController);
        showSelectedButton.addActionListener(administratorController);
        infoButton.addActionListener(administratorController);
        logOutButton.addActionListener(administratorController);

        JPanel importPanel = presentationFunctions.getPanelWithButton(importButton, "Import products from csv file: ");

        JPanel insertButtonPanel = presentationFunctions.getPanelWithButton(insertButton);
        JPanel titleFieldPanel = presentationFunctions.getPanelWithTextField(titleField, "Title: ");
        JPanel ratingFieldPanel = presentationFunctions.getPanelWithTextField(ratingField, "Rating: ");
        JPanel caloriesFieldPanel = presentationFunctions.getPanelWithTextField(caloriesField, "Calories: ");
        JPanel proteinFieldPanel = presentationFunctions.getPanelWithTextField(proteinField, "Protein: ");
        JPanel fatFieldPanel = presentationFunctions.getPanelWithTextField(fatField, "Fat: ");
        JPanel sodiumFieldPanel = presentationFunctions.getPanelWithTextField(sodiumField, "Sodium: ");
        JPanel priceFieldPanel = presentationFunctions.getPanelWithTextField(priceField, "Price: ");
        JPanel insertPanel = new JPanel();
        insertPanel.add(insertButtonPanel);
        insertPanel.add(titleFieldPanel);
        insertPanel.add(ratingFieldPanel);
        insertPanel.add(caloriesFieldPanel);
        insertPanel.add(proteinFieldPanel);
        insertPanel.add(fatFieldPanel);
        insertPanel.add(sodiumFieldPanel);
        insertPanel.add(priceFieldPanel);
        insertPanel.setLayout(new FlowLayout());

        JPanel updateButtonPanel = presentationFunctions.getPanelWithButton(updateButton);
        JPanel toSetComboBoxPanel = presentationFunctions.getPanelWithComboBox(toSetComboBox, "To set field");
        JPanel toSetValueFieldPanel = presentationFunctions.getPanelWithTextField(toSetValueField, "To set value: ");
        JPanel conditionComboBoxPanel = presentationFunctions.getPanelWithComboBox(conditionComboBox, "Condition field");
        JPanel conditionValueFieldPanel = presentationFunctions.getPanelWithTextField(conditionValueField, "Condition value: ");
        JPanel updatePanel = new JPanel();
        updatePanel.add(updateButtonPanel);
        updatePanel.add(toSetComboBoxPanel);
        updatePanel.add(toSetValueFieldPanel);
        updatePanel.add(conditionComboBoxPanel);
        updatePanel.add(conditionValueFieldPanel);
        updatePanel.setLayout(new FlowLayout());

        JPanel deleteButtonPanel = presentationFunctions.getPanelWithButton(deleteButton);
        JPanel deleteComboBoxPanel = presentationFunctions.getPanelWithComboBox(toDeleteComboBox, "");
        JPanel deletePanel = new JPanel();
        deletePanel.add(deleteButtonPanel);
        deletePanel.add(deleteComboBoxPanel);
        deletePanel.setLayout(new FlowLayout());

        JPanel createButtonPanel = presentationFunctions.getPanelWithButton(createButton);
        JPanel createFieldPanel = presentationFunctions.getPanelWithTextField(createField, "Title: ");
        JPanel menuComboBoxPanel = presentationFunctions.getPanelWithComboBox(menuComboBox, "");
        JPanel addButtonPanel = presentationFunctions.getPanelWithButton(addButton);
        JPanel createPanel = new JPanel();
        createPanel.add(createButtonPanel);
        createPanel.add(createFieldPanel);
        createPanel.add(menuComboBoxPanel);
        createPanel.add(addButtonPanel);
        createPanel.setLayout(new FlowLayout());

        JPanel selectedItemsPanel = presentationFunctions.getPanelWithButton(showSelectedButton);

        JPanel reportButtonPanel = presentationFunctions.getPanelWithButton(reportButton);
        JPanel reportComboBoxPanel = presentationFunctions.getPanelWithComboBox(reportComboBox, "");
        JPanel reportFieldPanel = presentationFunctions.getPanelWithTextField(reportField, "");
        JPanel infoButtonPanel = presentationFunctions.getPanelWithButton(infoButton);
        JPanel reportPanel = new JPanel();
        reportPanel.add(reportButtonPanel);
        reportPanel.add(reportComboBoxPanel);
        reportPanel.add(reportFieldPanel);
        reportPanel.add(infoButtonPanel);
        reportPanel.setLayout(new FlowLayout());

        JScrollPane resultPanel = presentationFunctions.getTextAreaPanel(resultTextArea);
        JPanel logOutPanel = presentationFunctions.getPanelWithButton(logOutButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(importPanel);
        mainPanel.add(insertPanel);
        mainPanel.add(updatePanel);
        mainPanel.add(deletePanel);
        mainPanel.add(createPanel);
        mainPanel.add(selectedItemsPanel);
        mainPanel.add(reportPanel);
        mainPanel.add(resultPanel);
        mainPanel.add(logOutPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public void resetFields() {
        titleField.setText("");
        ratingField.setText("");
        caloriesField.setText("");
        proteinField.setText("");
        fatField.setText("");
        sodiumField.setText("");
        priceField.setText("");
        toSetValueField.setText("");
        conditionValueField.setText("");
        createField.setText("");
        resultTextArea.setText("");
    }

    public void resetTextArea() {
        resultTextArea.setText("");
    }

    public JButton getImportButton() {
        return importButton;
    }

    public JButton getInsertButton() {
        return insertButton;
    }

    public String getTitleField() {
        return titleField.getText();
    }

    public String getRatingField() {
        return ratingField.getText();
    }

    public String getCaloriesField() {
        return caloriesField.getText();
    }

    public String getProteinField() {
        return proteinField.getText();
    }

    public String getFatField() {
        return fatField.getText();
    }

    public String getSodiumField() {
        return sodiumField.getText();
    }

    public String getPriceField() {
        return priceField.getText();
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JComboBox<String> getToSetComboBox() {
        return toSetComboBox;
    }

    public String getToSetValueField() {
        return toSetValueField.getText();
    }

    public JComboBox<String> getConditionComboBox() {
        return conditionComboBox;
    }

    public String getConditionValueField() {
        return conditionValueField.getText();
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JComboBox<String> getToDeleteComboBox() {
        return toDeleteComboBox;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public String getCreateField() {
        return createField.getText();
    }

    public JComboBox<String> getMenuComboBox() {
        return menuComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getShowSelectedButton() {
        return showSelectedButton;
    }

    public JButton getReportButton() {
        return reportButton;
    }

    public JComboBox<String> getReportComboBox() {
        return reportComboBox;
    }

    public String getReportField() {
        return reportField.getText();
    }

    public JButton getInfoButton() {
        return infoButton;
    }

    public void setResultTextArea(String resultText) {
        resultTextArea.setText(resultText);
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public void closeWindow() {
        frame.dispose();
    }
}
