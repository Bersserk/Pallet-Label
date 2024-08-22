package com.developer.bers.presentation;

import com.developer.bers.data.DocxFileFinder;
import com.developer.bers.data.SettingsTab;
import com.developer.bers.domain.models.Tab;
import com.developer.bers.presentation.surfaces.BaseJFrame;
import com.developer.bers.presentation.surfaces.DataForTab;

import javax.swing.*;
import java.awt.*;

public class MainView extends BaseJFrame {

    public MainView() throws HeadlessException {

        JTabbedPane tabPane = new JTabbedPane();

        new DocxFileFinder(AppProperties.get("input_folder")).getList().forEach(nameOfFile ->
                {
                    tabPane.addTab(nameOfFile, new DataForTab(new Tab(nameOfFile)));
                }
        );

        tabPane.addTab("Settings", new SettingsTab(tabPane));

        add(tabPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
