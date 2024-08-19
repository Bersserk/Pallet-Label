package com.developer.bers.data;

import com.developer.bers.helpers.CurrentWorkingDirectory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class SettingsApp {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JButton settingsButton;

    // Файл для хранения настроек
    private static final String SETTINGS_FILE = "settings.properties";
    // Директория, куда будут сохраняться выбранные файлы
    private static final String TARGET_DIRECTORY = System.getProperty("user.dir") + "/files/input/";

    private final Properties settings;

    public SettingsApp() {
        settings = new Properties();
        loadSettings();

        frame = new JFrame("Main Application");
        mainPanel = new JPanel();
        settingsButton = new JButton("Settings");

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettingsDialog();
            }
        });

        mainPanel.add(settingsButton);
        frame.add(mainPanel);

        applySettings();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void openSettingsDialog() {
        JDialog settingsDialog = new JDialog(frame, "Settings", true);
        JPanel settingsPanel = new JPanel(new GridLayout(5, 2));

        // File chooser button
        JButton fileButton = new JButton("Add .docx File");
        JLabel filePathLabel = new JLabel(settings.getProperty("file.path", "No file selected"));

        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Word Documents", "docx"));
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
        });

        // Frame size
        JLabel sizeLabel = new JLabel("Frame Size:");
        JComboBox<String> sizeComboBox = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        sizeComboBox.setSelectedItem(settings.getProperty("frame.size", "Small"));
        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSize = (String) sizeComboBox.getSelectedItem();
                settings.setProperty("frame.size", selectedSize);
                applySettings();
                saveSettings();
            }
        });

        // Button color
        JLabel buttonColorLabel = new JLabel("Button Color:");
        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color initialColor = settingsButton.getBackground();
                Color color = JColorChooser.showDialog(settingsDialog, "Select Button Color", initialColor);
                if (color != null) {
                    settingsButton.setBackground(color);
                    settings.setProperty("button.color", String.valueOf(color.getRGB()));
                    saveSettings();
                }
            }
        });

        // Background color
        JLabel backgroundColorLabel = new JLabel("Background Color:");
        JButton bgColorButton = new JButton("Choose Background Color");
        bgColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color initialColor = mainPanel.getBackground();
                Color color = JColorChooser.showDialog(settingsDialog, "Select Background Color", initialColor);
                if (color != null) {
                    mainPanel.setBackground(color);
                    settings.setProperty("background.color", String.valueOf(color.getRGB()));
                    saveSettings();
                }
            }
        });

        settingsPanel.add(fileButton);
        settingsPanel.add(sizeLabel);
        settingsPanel.add(sizeComboBox);
        settingsPanel.add(buttonColorLabel);
        settingsPanel.add(colorButton);
        settingsPanel.add(backgroundColorLabel);
        settingsPanel.add(bgColorButton);

        settingsDialog.add(settingsPanel);
        settingsDialog.pack();
        settingsDialog.setLocationRelativeTo(frame);
        settingsDialog.setVisible(true);
    }

    private void applySettings() {
        // Apply frame size
        String frameSize = settings.getProperty("frame.size", "Small");
        Dimension size;
        switch (frameSize) {
            case "Medium":
                size = new Dimension(600, 400);
                break;
            case "Large":
                size = new Dimension(800, 600);
                break;
            case "Small":
            default:
                size = new Dimension(400, 300);
                break;
        }
        frame.setSize(size);

        // Apply button color
        String buttonColorStr = settings.getProperty("button.color");
        if (buttonColorStr != null) {
            Color buttonColor = new Color(Integer.parseInt(buttonColorStr));
            settingsButton.setBackground(buttonColor);
        }

        // Apply background color
        String backgroundColorStr = settings.getProperty("background.color");
        if (backgroundColorStr != null) {
            Color backgroundColor = new Color(Integer.parseInt(backgroundColorStr));
            mainPanel.setBackground(backgroundColor);
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SettingsApp();
            }
        });
    }
}

