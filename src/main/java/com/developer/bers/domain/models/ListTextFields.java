package com.developer.bers.domain.models;

import javax.swing.*;
import java.util.HashMap;

public class ListTextFields <TextLabel, TextField> {
        TextLabel keyText;
        TextField fieldText;
        HashMap <TextLabel, TextField> list;

    public ListTextFields() {
        list = new HashMap<>();
    }

    public void put(TextLabel textLabel, TextField textField) {
        list.put(textLabel, textField);
    }
}
