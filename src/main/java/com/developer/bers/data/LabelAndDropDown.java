package com.developer.bers.data;

import com.developer.bers.data.repositories.GetDropDownList;
import com.developer.bers.data.store.ListsStore;
import com.developer.bers.data.store.LocalManagerBy;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class LabelAndDropDown extends JPanel implements RowComponent {

    private final LocalManagerBy localManager;
    private JComboBox<String> comboBox;
    private JLabel label;
    private JPanel jPanel;
    Map<String, String> listForDropDown;


    public LabelAndDropDown(LocalManagerBy localManager, String textLabel) {
        this.localManager = localManager;

        label = new JLabel(localManager.getLocalStringBy(textLabel));
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        GetDropDownList list = new ListsStore(localManager);

        comboBox = new JComboBox<>(list.getFor(textLabel));


        // Set the preferred size to stretch the combo box
        comboBox.setPreferredSize(new Dimension(200, 40)); // Width: 200, Height: 25

        // Optionally, make the JComboBox editable
        comboBox.setEditable(false);
//        comboBox.setSelectedItem(localManager.getLocalStringBy("this"));


        // Add the JComboBox to the JPanel
        jPanel.add(comboBox);

    }

    @Override
    public JComponent get() {
        this.add(label);
        this.add(jPanel);
        return this;
    }

    @Override
    public String getLabel() {
        return label.getText();
    }

    @Override
    public String getField() {
        return Objects.requireNonNull(comboBox.getSelectedItem()).toString();
    }

    @Override
    public void setTextToField(String text) {
        comboBox.setSelectedItem(localManager.getLocalStringBy(text));

    }
}

