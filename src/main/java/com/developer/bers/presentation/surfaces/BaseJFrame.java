package com.developer.bers.presentation.surfaces;

import com.developer.bers.presentation.models.MainFrame;

import javax.swing.*;
import java.awt.*;

public abstract class BaseJFrame extends JFrame {

    public BaseJFrame() throws HeadlessException {
        MainFrame mainFrame = new MainFrame("Pallet Label", 1000, 800);

        setTitle(mainFrame.nameForFrame());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(mainFrame.width(), mainFrame.length());
        setLocationRelativeTo(null);


        setVisible(true);


    }
}
