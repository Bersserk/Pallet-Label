package com.developer.bers.presentation.surfaces;

import com.developer.bers.data.LabelAndDropDown;

import javax.swing.*;
import java.awt.*;

public class RowOfTextAndField extends JPanel {

    private JPanel panel;
    private final JLabel textLabel;
    private final JTextField customField;
    private final LabelAndDropDown dropDown;

    public RowOfTextAndField(JLabel textLabel, JTextField customField) {
        this.textLabel = textLabel;
        this.customField = customField;
        this.dropDown = null;

        createPanel();
        addComponentsToPanel();
        this.add(panel);
    }

    public RowOfTextAndField(JLabel textLabel, LabelAndDropDown dropDown) {
        this.textLabel = textLabel;
        this.customField = null;
        this.dropDown = dropDown;

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
        if (textLabel != null) {
            panel.add(textLabel);
        }
        if (customField != null) {
            panel.add(customField);
        }
        if (dropDown != null) {
            panel.add(dropDown);
        }
    }


}
