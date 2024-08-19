package com.developer.bers;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleApp {
    public static void main(String[] args) {
        // Создаем главное окно
        JFrame frame = new JFrame("Simple App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        // Создаем два текстовых поля
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();

        // Создаем две кнопки
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Save to Word");

        // Создаем чекбокс в виде выпадающего меню
        JCheckBox checkBox = new JCheckBox("Option 1");

        // Добавляем обработчики событий для кнопок
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 1 clicked");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                boolean option = checkBox.isSelected();
                createWordDocument(text1, text2, option);
            }
        });

        // Добавляем компоненты на главное окно
        frame.add(textField1);
        frame.add(textField2);
        frame.add(button1);
        frame.add(button2);
        frame.add(checkBox);

        // Делаем окно видимым
        frame.setVisible(true);
    }

    // Метод для создания Word-документа
    public static void createWordDocument(String text1, String text2, boolean option) {
        XWPFDocument document = new XWPFDocument();

        // Создаем параграфы и добавляем текст
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun run1 = paragraph1.createRun();
        run1.setText("Text from field 1: " + text1);

        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        run2.setText("Text from field 2: " + text2);

        XWPFParagraph paragraph3 = document.createParagraph();
        XWPFRun run3 = paragraph3.createRun();
        run3.setText("Checkbox selected: " + option);

        // Сохраняем документ
        try (FileOutputStream out = new FileOutputStream(new File("c:\\Developer\\testDoc.docx"))) {
            document.write(out);
            System.out.println("Word document created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

