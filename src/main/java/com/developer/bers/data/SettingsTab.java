package com.developer.bers.data;

import com.developer.bers.data.store.LocalManagerBy;
import com.developer.bers.domain.frameworks.StringFormatter;
import com.developer.bers.presentation.AppProperties;
import com.developer.bers.presentation.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsTab extends JPanel {
    // Файл для хранения настроек
    private static final String SETTINGS_FILE = AppProperties.get("properties");
    public static final String SAVE_SETTINGS = "save_settings";
    public static final String LANGUAGE = "language";
    public static final String WIDTH_MAIN_SCREEN = "width_main_screen";
    public static final String LENGTH_MAIN_SCREEN = "length_main_screen";
    public static final String FONT_SIZE = "font_size";
    // Директория, куда будут сохраняться выбранные файлы

    private final LocalManagerBy localManager;
    private final MainView mainView;
    public RowComponent widthOfScreen;
    public RowComponent fontSize;
    public RowComponent dropDownLanguage;
    private JPanel thisPanel;
    private GridBagConstraints gbc;

    private final Map<String, JComponent> fieldMap = new HashMap<>();

    public RowComponent lengthOfScreen;


    public SettingsTab(LocalManagerBy localManager, MainView mainView) {
        this.localManager = localManager;
        this.mainView = mainView;
        createPanel();
        loadSettings();
        addComponentsToPanel();
    }

    private void addComponentsToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;


        lengthOfScreen = new LabelAndField(
                localManager, LENGTH_MAIN_SCREEN, 5, 20, 1000);

        thisPanel.add(lengthOfScreen.get(), gbc);
        gbc.gridwidth++;
        fieldMap.put(LENGTH_MAIN_SCREEN, lengthOfScreen.get());


        widthOfScreen = new LabelAndField(
                localManager, WIDTH_MAIN_SCREEN, 5, 20, 600);
        thisPanel.add(widthOfScreen.get(), gbc);
        gbc.gridwidth++;
        fieldMap.put(WIDTH_MAIN_SCREEN, widthOfScreen.get());


        fontSize = new LabelAndField(
                localManager, FONT_SIZE, 5, 20, 20);
        thisPanel.add(fontSize.get(), gbc);
        fieldMap.put(FONT_SIZE, fontSize.get());


        dropDownLanguage = new LabelAndDropDown(localManager, LANGUAGE);
        thisPanel.add(dropDownLanguage.get(), gbc);
        fieldMap.put(LANGUAGE, dropDownLanguage.get());


        addCustomButton(4, 2, 2,
                localManager.getLocalStringBy(SAVE_SETTINGS), a -> saveSettings());


//        addCustomButton(5, 0, 2, "Add Template", a -> manager.addTemplate(tabPane));
//
//        addCustomButton(6, 0, 2, "Remove Template", a -> manager.removeTemplate(tabPane));

    }

    private void addCustomButton(int numRow, int numColumn, int gridWidth, String textOfButton, ActionListener action) {
        System.out.println("addCustomButton from SettingsTab");
        gbc.gridy = numRow;
        gbc.gridx = numColumn;
        gbc.gridwidth = gridWidth;
        gbc.insets = new Insets(10, 0, 0, 10);
        JButton button = new JButton(textOfButton);
        button.addActionListener(action);
        thisPanel.add(button, gbc);
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
        System.out.println("method saveSettings from SettingsTab");

        // Сохраняем текущие значения в properties
        for (Map.Entry<String, JComponent> entry : fieldMap.entrySet()) {
            String value = ((RowComponent) entry.getValue()).getField();
            RowComponent g = ((RowComponent) entry.getValue());

            if (entry.getKey().equals("language")) {
                if (localManager.getLocalStringBy("english").equals(value)) {
                    AppProperties.setProperty("local", "en");
                } else if (localManager.getLocalStringBy("polish").equals(value)) {
                    AppProperties.setProperty("local", "pl");
                } else if (localManager.getLocalStringBy("ukrainian").equals(value)) {
                    AppProperties.setProperty("local", "ua");
                } else if (localManager.getLocalStringBy("russian").equals(value)) {
                    AppProperties.setProperty("local", "ru");
                }
                mainView.changeLanguage();

            } else {
                String key = entry.getKey();
                String formatedKey = new StringFormatter().formatedForKey(key);
                AppProperties.setProperty(formatedKey, value);

                mainView.changeProperties();
            }
        }

        mainView.fillSavedSettingTab();

    }

    private void loadSettings() {
        try (FileInputStream in = new FileInputStream(SETTINGS_FILE)) {
            AppProperties.load(in);
        } catch (IOException e) {
            // If file does not exist, default settings will be used
        }
    }


}
