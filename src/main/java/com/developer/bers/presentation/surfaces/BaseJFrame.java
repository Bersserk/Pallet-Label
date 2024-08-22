package com.developer.bers.presentation.surfaces;

import com.developer.bers.presentation.AppProperties;
import com.developer.bers.presentation.models.MainFrame;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.parseInt;

public abstract class BaseJFrame extends JFrame {

    public BaseJFrame() throws HeadlessException {
        // Получение значений из AppProperties
        String nameApp = AppProperties.get("name_app");
        int width = AppProperties.getNum("width_main_screen", 1000);
        int length = AppProperties.getNum("length_main_screen", 800);

        // Инициализация MainFrame с использованием значений из AppProperties
        MainFrame mainFrame = new MainFrame(nameApp, width, length);

        // Настройка JFrame
        setTitle(mainFrame.nameForFrame());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(mainFrame.width(), mainFrame.length());
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
