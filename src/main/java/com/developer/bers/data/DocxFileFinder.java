package com.developer.bers.data;

import com.developer.bers.domain.repositories.GetListNamesFilesFromFolder;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DocxFileFinder implements GetListNamesFilesFromFolder {

    private final String directoryPath;

    public DocxFileFinder(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    private List<String> getDocxFiles() {
        List<String> docxFiles = new ArrayList<>();

        // Создаем объект File для директории
        File directory = new File(directoryPath);

        // Проверяем, что это действительно директория
        if (directory.isDirectory()) {
            // Используем FilenameFilter для фильтрации файлов по расширению
            FilenameFilter docxFilter = (dir, name) -> name.toLowerCase().endsWith(".docx");

            // Получаем список файлов, соответствующих фильтру
            File[] files = directory.listFiles(docxFilter);

            if (files != null) {
                for (File file : files) {
                    // Добавляем имя файла в список
                    docxFiles.add(file.getName());
                }
            }
        } else {
            System.out.println("Путь не является директорией: " + directoryPath);
        }

        return docxFiles;
    }

    @Override
    public List<String> getList() throws NoSuchElementException {
        // Пример использования класса
        DocxFileFinder finder = new DocxFileFinder(directoryPath);

        List<String> docxFiles = finder.getDocxFiles();

        if (docxFiles.isEmpty()) {
            // Показываем диалоговое окно с предупреждением
            JOptionPane.showMessageDialog(null,
                    "Файлы .docx не найдены. Пожалуйста, добавьте хотя бы один файл.",
                    "Предупреждение",
                    JOptionPane.WARNING_MESSAGE);

            // Бросаем исключение
            throw new NoSuchElementException("Файлы .docx не найдены.");
        }

        return docxFiles;
    }
}

