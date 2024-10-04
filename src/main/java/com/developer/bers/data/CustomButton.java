package com.developer.bers.data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {
    // Constructor accepts a Runnable (functional interface) as the action to be invoked
    public CustomButton(String buttonText, Runnable action) {
        super(buttonText);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                action.run();  // This invokes the function passed as a parameter
            }
        });
    }

    public GridBagConstraints getGbc(int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 15, 0, 15);
        gbc.gridx = 5;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        return gbc;
    }
}
