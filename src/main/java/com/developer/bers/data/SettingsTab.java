package com.developer.bers.data;

import com.developer.bers.domain.models.Tab;
import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.usecases.ManagerTabComponentsUseCase;
import com.developer.bers.presentation.surfaces.RowOfTextAndField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsTab extends JPanel {
    // Файл для хранения настроек
    private static final String SETTINGS_FILE = "settings.properties";
    // Директория, куда будут сохраняться выбранные файлы
    private static final String TARGET_DIRECTORY = System.getProperty("user.dir") + "/files/input/";

    private final Properties settings;
    private final JTabbedPane tabPane;
    private JPanel thisPanel;
    private GridBagConstraints gbc;

    private ManagerTabComponentsUseCase manager = new ManagerTabComponentsUseCase();

//    private AddingTemplate addingTemplate;


    public SettingsTab(JTabbedPane tabPane) {
        this.tabPane = tabPane;
        createPanel();
        settings = new Properties();
//        addingTemplate = new AddingTemplate();
        loadSettings();
        addComponentsToPanel();
    }

    private void addComponentsToPanel() {

        addCustomRowOfTextAndField(0, 0, 1, "first", 15);
        addCustomRowOfTextAndField(1, 0, 1, "fffff", 15);
        addCustomRowOfTextAndField(2, 0, 1, "second", 15);
//        createComponent(2, 1, 2, list.get(2), 15);
//        createComponent(4, 1, 2, list.get(3), 15);
//        createComponent(0, 2, MAIN_PANEL, list.get(4), 50);
//        createComponent(0, 3, 2, list.get(5), 12);
//        createComponent(2, 3, 2, list.get(6), 12);
//        createComponent(0, 4, 2, list.get(7), 12);
//        createComponent(2, 4, 2, list.get(8), 12);
//        createComponent(0, 5, MAIN_PANEL, list.get(9), 50);


//        gbc.insets = new Insets(0,15,0,15);



        addCustomButton(3, 0, 2, "Add Template", a -> manager.addTemplate(tabPane));

        addCustomButton(4, 0, 2, "Remove Template", a -> manager.removeTemplate(tabPane));



    }

    private void addCustomButton(int numRow, int numColumn, int gridWidth, String textOfButton, ActionListener action) {
        System.out.println("addCustomButton from SettingsTab");
        gbc.gridy = numRow;
        gbc.gridx = numColumn;
        gbc.gridwidth = gridWidth;
        gbc.insets = new Insets(10,0,0,10);
        JButton button = new JButton(textOfButton);
        button.addActionListener(action);
        thisPanel.add(button, gbc);
    }

    private void addCustomRowOfTextAndField(int numRow, int numColumn, int gridWidth, String textOfLabel, int quantityColumnsForTextField) {
        gbc.gridy = numRow;
        gbc.gridx = numColumn;
        gbc.gridwidth = gridWidth;
        Tab tab = new Tab(textOfLabel);
        JTextField customField = new CustomField(quantityColumnsForTextField);
        thisPanel.add(new RowOfTextAndField(tab, customField), gbc);
    }

    private void createPanel() {
        thisPanel = new JPanel(new GridBagLayout());
        thisPanel.setLayout(new GridBagLayout());
        this.add(thisPanel);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        thisPanel.setVisible(true);
    }

    private void saveSettings() {
        try (FileOutputStream out = new FileOutputStream(SETTINGS_FILE)) {
            settings.store(out, "Application Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSettings() {
        try (FileInputStream in = new FileInputStream(SETTINGS_FILE)) {
            settings.load(in);
        } catch (IOException e) {
            // If file does not exist, default settings will be used
        }
    }


}
