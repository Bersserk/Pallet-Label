package com.developer.bers.data.repositories;

import com.developer.bers.domain.repositories.PressButtonAddTemplate;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;


public class AddableTemplate implements PressButtonAddTemplate {

    private static final String TARGET_DIRECTORY = System.getProperty("user.dir") + "/files/input/";

    // Указание пути к файлу настроек
    private static final String SETTINGS_FILE = "settings.properties";

    Properties settings;


    @Override
    public void execute() {
        System.out.println("Template was added");

        settings = new Properties();


        JDialog settingsDialog = new JDialog(new Frame(), "Settings", true);

        // File chooser button
        JLabel filePathLabel = new JLabel(settings.getProperty("file.path", "No file selected"));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Word Documents", "docs"));
        int returnValue = fileChooser.showOpenDialog(settingsDialog);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                File targetFile = new File(TARGET_DIRECTORY + selectedFile.getName());
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(settingsDialog, "File saved to: " + targetFile.getAbsolutePath());
                settings.setProperty("file.path", selectedFile.getAbsolutePath());
                filePathLabel.setText(selectedFile.getAbsolutePath());
                saveSettings();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(settingsDialog, "Error saving file: " + ex.getMessage());
            }
        }


    }

    private void saveSettings() {
        try (FileOutputStream out = new FileOutputStream(SETTINGS_FILE)) {
            settings.store(out, "Application Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
