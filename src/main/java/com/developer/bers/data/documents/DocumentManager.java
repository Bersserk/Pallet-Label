package com.developer.bers.data.documents;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Properties;

public class DocumentManager {

    private String selectedDirectory;
    private final Properties properties = new Properties();
    private final String text = "text";

    // Конструктор, который загружает настройки из properties
    public DocumentManager() {
        loadProperties();
    }

    // Метод для отображения диалогового окна выбора папки
    public void chooseDirectoryAndSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            selectedDirectory = selectedFolder.getAbsolutePath();
            saveDirectoryToProperties(selectedDirectory);
        } else {
            System.out.println("Папка не выбрана");
        }
    }

    // Метод для сохранения пути к папке в файле settings.properties
    private void saveDirectoryToProperties(String directoryPath) {
        try (FileOutputStream output = new FileOutputStream("settings.properties")) {
            properties.setProperty("docx_directory", directoryPath);
            properties.store(output, "Document Directory Settings");
            System.out.println("Путь к папке сохранен в settings.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для загрузки пути к папке из файла settings.properties
    private void loadProperties() {
        try (InputStream input = new FileInputStream("settings.properties")) {
            properties.load(input);
            selectedDirectory = properties.getProperty("docx_directory");
        } catch (IOException e) {
            System.out.println("Файл settings.properties не найден. Создайте файл вручную или выберите папку.");
        }
    }

    // Метод для получения списка файлов с расширением .docx из выбранной папки
    public Map<String, XWPFDocument> getDocxFiles() {
        if (selectedDirectory == null) {
            System.out.println("Папка с документами не выбрана.");
            return Collections.emptyMap();
        }

        File dir = new File(selectedDirectory);
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".docx"));

        if (files == null || files.length == 0) {
            System.out.println("Файлы .docx не найдены.");
            return Collections.emptyMap();
        }

        Map<String, File> docxFiles = new HashMap<>();
        for (File file : files) {
            docxFiles.put(file.getName(), file);
        }

        return docxFiles;
    }

//    public static void main(String[] args) {
//        DocumentManager manager = new DocumentManager();
//
//        // Если нужно выбрать новую директорию
//        manager.chooseDirectoryAndSave();
//
//        // Получаем список файлов .docx из выбранной директории
//        Map<String, File> docxFiles = manager.getDocxFiles();
//
//        // Выводим список файлов
//        if (!docxFiles.isEmpty()) {
//            docxFiles.forEach((name, file) -> System.out.println("Найден файл: " + name));
//        }
//    }
}
