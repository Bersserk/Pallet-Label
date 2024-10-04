package com.developer.bers.presentation.surfaces;

import javax.swing.*;
import java.awt.*;

import static com.developer.bers.presentation.AppProperties.*;

public abstract class BaseJFrame extends JFrame {

    public BaseJFrame() throws HeadlessException {

        // Настройка JFrame
        setTitle(get("name_app"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getNum("length_main_screen", 1000),
                getNum("width_main_screen", 800));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void recomposition() {
        System.out.println("recomposition from BaseFrame");
        setSize(getNum("length_main_screen", 100), getNum("width_main_screen", 100));
    }


}
