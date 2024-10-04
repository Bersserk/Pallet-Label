package com.developer.bers.data;

import javax.swing.*;

public interface RowComponent{

    JComponent get();
    String getLabel();
    String getField();
    void setTextToField(String text);


}
