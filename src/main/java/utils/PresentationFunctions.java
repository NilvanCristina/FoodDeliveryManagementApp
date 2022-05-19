package utils;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class PresentationFunctions {
    @NotNull
    public JPanel getPanelWithTextField(JTextField field, String labelText) {
        JPanel newPanel = new JPanel();
        JLabel newLabel = new JLabel(labelText);

        newPanel.add(newLabel);
        newPanel.add(field);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JPanel getPanelWithButton(JButton button) {
        JPanel newPanel = new JPanel();

        newPanel.add(button);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JPanel getPanelWithButton(JButton button, String labelText) {
        JPanel newPanel = new JPanel();
        JLabel newLabel = new JLabel(labelText);

        newPanel.add(newLabel);
        newPanel.add(button);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JPanel getPanelWithComboBox(JComboBox<String> comboBox, String labelText) {
        JPanel newPanel = new JPanel();
        JLabel newLabel = new JLabel(labelText);

        newPanel.add(newLabel);
        newPanel.add(comboBox);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JPanel getPanelWithComboBox(JComboBox<String> comboBox, JButton button, JTextField textField) {
        JPanel newPanel = new JPanel();

        newPanel.add(button);
        newPanel.add(comboBox);
        newPanel.add(textField);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JPanel getPanelWithComboBoxAndButtons(JComboBox<String> comboBox, JButton firstButton, JButton secondButton) {
        JPanel newPanel = new JPanel();

        newPanel.add(firstButton);
        newPanel.add(comboBox);
        newPanel.add(secondButton);
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    @NotNull
    public JScrollPane getTextAreaPanel(JTextArea content) {
        JScrollPane jScrollPanel = new JScrollPane(content);
        jScrollPanel.setPreferredSize(new Dimension(480, 400));

        return jScrollPanel;
    }
}
