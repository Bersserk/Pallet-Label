package com.developer.bers.presentation.surfaces;

import javax.swing.*;
import java.awt.*;

public class RowOfTextAndField extends JPanel {

    private JPanel panel;
    private final JLabel textLabel;
    private final JTextField customField;

    public RowOfTextAndField(JLabel textLabel, JTextField customField) {
        this.textLabel = textLabel;
        this.customField = customField;

        createPanel();
        addComponentsToPanel();
        this.add(panel);
    }


    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(Color.CYAN);
    }

    private void addComponentsToPanel() {
        panel.add(textLabel);
        panel.add(customField);
    }



}
