package com.developer.bers.domain.models;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomLabel extends JLabel {

    public CustomLabel(String text) {
        super(text);  // Устанавливаем текст для текущего объекта JLabel
        setFont(new Font("Arial", Font.BOLD, 14)); // Устанавливаем шрифт

        setOpaque(true);   // Делаем JLabel непрозрачным
        setBackground(Color.BLUE); // Устанавливаем цвет фона
        setForeground(Color.GREEN); // Устанавливаем цвет текста
        setBorder(new EmptyBorder(10, 10, 10, 10));

        setVisible(true); // Делаем JLabel видимым
    }



}
