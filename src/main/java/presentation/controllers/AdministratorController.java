package presentation.controllers;

import presentation.AdministratorGUI;
import presentation.MainGUI;
import utils.UsedFunctions;
import business.DeliveryService;
import data.Serializator;
import data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorController implements ActionListener {
    private AdministratorGUI administratorGUI;
    private DeliveryService deliveryService;
    private User administrator;

    public AdministratorController(AdministratorGUI administratorGUI, DeliveryService deliveryService, User administrator) {
        this.administratorGUI = administratorGUI;
        this.deliveryService = deliveryService;
        this.administrator = administrator;
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if (button.getSource() == administratorGUI.getImportButton()) {
            deliveryService.importProducts();
        }

        if (button.getSource() == administratorGUI.getInsertButton()) {
            String title = administratorGUI.getTitleField();
            double rating = Double.parseDouble(administratorGUI.getRatingField());
            int calories = Integer.parseInt(administratorGUI.getCaloriesField());
            int protein = Integer.parseInt(administratorGUI.getProteinField());
            int fat = Integer.parseInt(administratorGUI.getFatField());
            int sodium = Integer.parseInt(administratorGUI.getSodiumField());
            double price = Double.parseDouble(administratorGUI.getPriceField());

            deliveryService.addMenuItem(title, rating, calories, protein, fat, sodium, price);
        }

        if (button.getSource() == administratorGUI.getUpdateButton()) {
            String toSetField = String.valueOf(administratorGUI.getToSetComboBox().getSelectedItem());
            String toSetValue = administratorGUI.getToSetValueField();
            String conditionField = String.valueOf(administratorGUI.getConditionComboBox().getSelectedItem());
            String conditionValue = administratorGUI.getConditionValueField();

            deliveryService.editMenuItem(toSetField, toSetValue, conditionField, conditionValue);
        }

        if (button.getSource() == administratorGUI.getDeleteButton()) {
            String toDeleteItem = String.valueOf(administratorGUI.getToDeleteComboBox().getSelectedItem());
            deliveryService.deleteMenuItem(toDeleteItem);
        }

        if (button.getSource() == administratorGUI.getAddButton()) {
            String menuItemTitle = String.valueOf(administratorGUI.getMenuComboBox().getSelectedItem());
            deliveryService.addToList(menuItemTitle, "composite");
        }

        if (button.getSource() == administratorGUI.getShowSelectedButton()) {
            String compositeItem = deliveryService.getListContent("composite");
            administratorGUI.resetTextArea();
            administratorGUI.setResultTextArea(compositeItem);
        }

        if (button.getSource() == administratorGUI.getCreateButton()) {
            String message = "The item has been created successfully!";
            String title = administratorGUI.getCreateField();

            deliveryService.createMenuItem(title);
            deliveryService.emptyList("composite");

            JOptionPane.showMessageDialog(administratorGUI, message, "", JOptionPane.INFORMATION_MESSAGE);
        }

        if (button.getSource() == administratorGUI.getInfoButton()) {
            String reportName = String.valueOf(administratorGUI.getReportComboBox().getSelectedItem());
            String reportInfo = UsedFunctions.getReportInfo(reportName);
            JOptionPane.showMessageDialog(administratorGUI, reportInfo, "Report Info", JOptionPane.INFORMATION_MESSAGE);
        }

        if (button.getSource() == administratorGUI.getReportButton()) {
            String reportName = String.valueOf(administratorGUI.getReportComboBox().getSelectedItem());
            String reportCriteria = administratorGUI.getReportField();
            String reportResult = deliveryService.generateReport(reportName, reportCriteria);

            administratorGUI.resetTextArea();
            administratorGUI.setResultTextArea(reportResult);
        }

        if (button.getSource() == administratorGUI.getLogOutButton()) {
            administratorGUI.closeWindow();
            MainGUI mainGUI = new MainGUI(deliveryService);
            Serializator.serialize(deliveryService);
        }
    }
}
