package com.developer.bers.domain.frameworks;

import com.developer.bers.domain.repositories.AppConst;
import com.developer.bers.domain.repositories.SaveNewDocumentRepository;
import com.developer.bers.presentation.AppProperties;
import com.developer.bers.data.RowComponent;
import com.developer.bers.presentation.surfaces.ListRowsBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DocumentSaveable {

    public void saveDoc(XWPFDocument document, String fileName) {

//        XWPFDocument someDoc = getDoc(listRows);


        // Сохранение DOCX документа

        try (FileOutputStream out = new FileOutputStream(AppProperties.get(AppConst.OUTPUT_FOLDER) + "\\" + fileName)) {
            document.write(out);
            System.out.println("Document created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XWPFDocument getDoc(ListRowsBuilder<RowComponent> listRows) {
        XWPFDocument doc = null;
        String dir = AppProperties.get(AppConst.INPUT_FOLDER) + listRows.getName();

        try (FileInputStream fis = new FileInputStream(dir)) {
//            doc = new FillerDocument(new XWPFDocument(fis), listRows.getListOfCustomRows()).getFilledDoc();

            // Обработка таблиц
            for (XWPFTable table : doc.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            List<XWPFRun> runs = paragraph.getRuns();
                            for (XWPFRun run : runs) {
                                String text = run.getText(0);

                                listRows.getListOfCustomRows().forEach(it -> {
                                    if (text != null && text.contains(it.getLabel())) {
                                        String updatedRunText = text.replace(text, it.getField());
                                        run.setText(updatedRunText, 0);
                                    }
                                });
                            }
                        }
                    }
                }
            }

            System.out.println("Text replaced and new document saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private String getTextFromTextField (String inText, List<RowComponent> listRows){
        String str = null;

        for (int i = 0; i < listRows.size(); i++){
            if (listRows.get(i).getLabel().equals(inText)){
                str = listRows.get(i).getField();
                break;
            }
        }

        return str;
    }

    public void replaceTextInParagraphs(List<XWPFParagraph> paragraphs, List<RowComponent> listRows) {
        for (XWPFParagraph paragraph : paragraphs) {
            String paragraphText = paragraph.getText();

            // Разбиваем параграф на части по разделению с двумя или более пробелами
            String[] parts = paragraphText.split(" {2,}");

            // Если нет двух или более пробелов, заменяем весь текст
            if (parts.length == 1) {
                String newText = getTextFromTextField(paragraphText, listRows);
                replaceParagraphText(paragraph, newText);
            } else {
                // Если нашли разделение, заменяем части текста по отдельности
                StringBuilder newParagraphText = new StringBuilder();

                for (int i = 0; i < parts.length; i++) {
                    // Заменяем текст до второго пробела
//                    newParagr`aphText.append(listRows);

                    // Добавляем пробелы, которые были между частями текста
                    if (i < parts.length - 1) {
                        newParagraphText.append("  "); // Добавляем два пробела
                    }
                }

                replaceParagraphText(paragraph, newParagraphText.toString());
            }
        }
    }

    // Метод для замены текста в параграфе
    private void replaceParagraphText(XWPFParagraph paragraph, String newText) {
        // Удаляем все старые runs
        for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }

        // Создаем новый run с обновленным текстом
        XWPFRun run = paragraph.createRun();
        run.setText(newText);
    }
}
