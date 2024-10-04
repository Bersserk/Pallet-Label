package com.developer.bers;

import com.developer.bers.presentation.MainView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::run);
    }

    private static void run() {
        MainView ex = new MainView();
        ex.setVisible(true);

    }
}
