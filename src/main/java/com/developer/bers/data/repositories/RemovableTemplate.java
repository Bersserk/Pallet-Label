package com.developer.bers.data.repositories;

import com.developer.bers.domain.repositories.PressButtonRemoveTemplate;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RemovableTemplate implements PressButtonRemoveTemplate {

    private static final String TARGET_DIRECTORY = System.getProperty("user.dir") + "/files/input/";

    @Override
    public void execute() {
        System.out.println("Removing template...");

        JDialog settingsDialog = new JDialog(new Frame(), "Remove File", true);

        // File chooser button
        JFileChooser fileChooser = new JFileChooser(TARGET_DIRECTORY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Word Documents", "docs"));
        int returnValue = fileChooser.showOpenDialog(settingsDialog);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Path filePath = selectedFile.toPath();
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    JOptionPane.showMessageDialog(settingsDialog, "File deleted: " + selectedFile.getName());
                } else {
                    JOptionPane.showMessageDialog(settingsDialog, "File does not exist: " + selectedFile.getName());
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(settingsDialog, "Error deleting file: " + ex.getMessage());
            }
        }
    }
}
