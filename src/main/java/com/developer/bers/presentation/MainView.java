package com.developer.bers.presentation;

import com.developer.bers.data.DocxFileFinder;
import com.developer.bers.data.SettingsTab;
import com.developer.bers.domain.models.CustomLabel;
import com.developer.bers.domain.repositories.GetListNamesFilesFromFolder;
import com.developer.bers.presentation.surfaces.BaseJFrame;
import com.developer.bers.presentation.surfaces.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MainView extends BaseJFrame {

    // Директория, куда будут сохраняться выбранные файлы
    private final String TARGET_DIRECTORY = System.getProperty("user.dir") + "/files/input/";

    public MainView() throws HeadlessException {

        JTabbedPane tabPane = new JTabbedPane();

        new DocxFileFinder(TARGET_DIRECTORY).getList().forEach(item ->
                {
                    tabPane.addTab(item, new MainPanel(new CustomLabel(item)));
                }
        );

        tabPane.addTab("Settings", new SettingsTab(tabPane));

        add(tabPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
