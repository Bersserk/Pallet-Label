package com.developer.bers.presentation.surfaces;

import com.developer.bers.data.models.Paragraph;
import com.developer.bers.domain.models.FilePaths;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListParagraphBuilder {

    private final String nameCustomLabel;
    private FilePaths filePaths = new FilePaths(
            "files/input/",
            "files/output/Document.docx");

    private XWPFDocument document;
    private List<Paragraph> listParagraphs;



    public XWPFDocument getDocument() {
        return document;
    }
    public List<Paragraph> getList() {

        return listParagraphs;
    }





    public ListParagraphBuilder(String nameCustomLabel) {
        this.nameCustomLabel = nameCustomLabel;
        try (FileInputStream fis = new FileInputStream(filePaths.inputFilePath() + nameCustomLabel);
             XWPFDocument doc = new XWPFDocument(fis)) {
            document = doc;

            listParagraphs = new ArrayList<>();

            // Replace text in paragraphs

//            for (String item: list){



            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                if (text.contains("  ")) {
                    listParagraphs.addAll(splitTextByItems(paragraph));
                } else {
                    listParagraphs.add(new Paragraph(paragraph, nameCustomLabel));
                }
            }

            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            String text = paragraph.getText();
                            if (text.contains("  ")) {
                                listParagraphs.addAll(splitTextByItems(paragraph));
                            } else {
                                listParagraphs.add(new Paragraph(paragraph, nameCustomLabel));
                            }
                        }
                    }
                }
            }


            // Save the modified document
//            try (FileOutputStream fos = new FileOutputStream(filePaths.outputFilePath())) {
//                document.write(fos);
//            }

            System.out.println("Text replaced and new document saved successfully!");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Paragraph> splitTextByItems(XWPFParagraph inputParagraph) {
        // Получаем текст из входного параграфа
        String inputText = inputParagraph.getText();

        // Разделяем текст по двум и более пробелам
        String[] words = inputText.split("\\s{2,}"); // \\s{2,} - разделение по 2 и более пробелам

        // Создаем новый XWPFDocument для хранения параграфов
        XWPFDocument document = new XWPFDocument();

        // Создаем список для хранения разделенных параграфов
        List<Paragraph> result = new ArrayList<>();

        // Проходим по каждому разделенному фрагменту текста
        for (String word : words) {
            if (!word.trim().isEmpty()) { // Пропускаем пустые строки
                // Создаем новый параграф в документе
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.createRun().setText(word.trim());

                // Добавляем параграф в список
                result.add(new Paragraph(paragraph, nameCustomLabel));
            }
        }

        return result;
    }

}
