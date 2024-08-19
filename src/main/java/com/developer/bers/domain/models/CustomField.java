package com.developer.bers.domain.models;

import javax.swing.*;

public class CustomField extends JTextField {

    public CustomField(int columns) {
        super(columns < 12 ? 100 : columns);
    }
}
