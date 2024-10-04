package com.developer.bers;

import javax.swing.*;
import java.awt.*;

public class TestMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dropdown Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        DropDownField2 dropDownField = new DropDownField2();
        frame.add(dropDownField);

        frame.setVisible(true);
    }
}

class DropDownField2 extends JPanel {

    private JComboBox<String> comboBox;
    private JLabel label;

    public DropDownField2() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // Create a JComboBox with some options
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
        comboBox = new JComboBox<>(options);

        // Optionally, make the JComboBox editable
        comboBox.setEditable(true);

        // Add the JComboBox to the JPanel
        add(comboBox);

//        // Add a button to show the selected item
//        JButton button = new JButton("Show Selected Item");
//        add(button);
//
//        // Add a label to display the selected item
//        label = new JLabel("Selected Item: ");
//        add(label);
//
//        // ActionListener to show the selected item
//        button.addActionListener(e -> {
//            String selectedItem = (String) comboBox.getSelectedItem();
//            label.setText("Selected Item: " + selectedItem);
//        });
    }

}


