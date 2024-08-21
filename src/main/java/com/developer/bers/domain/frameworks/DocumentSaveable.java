package com.developer.bers.domain.frameworks;

import com.developer.bers.domain.repositories.SaveNewDocumentRepository;
import com.developer.bers.helpers.CurrentWorkingDirectory;
import com.developer.bers.presentation.AppProperties;
import com.developer.bers.presentation.surfaces.CustomRow;
import com.developer.bers.presentation.surfaces.ListRows;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DocumentSaveable implements SaveNewDocumentRepository {

    @Override
    public void saveDoc(ListRows <CustomRow> listRows) {

        XWPFDocument someDoc = getDoc(listRows);

        // Сохранение DOCX документа

        try (FileOutputStream out = new FileOutputStream(AppProperties.get("outputFolder") + "\\" + listRows.getName())) {
            someDoc.write(out);
            System.out.println("Document created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XWPFDocument getDoc(ListRows<CustomRow> listRows) {
        XWPFDocument doc = null;
        String dir = AppProperties.get("inputFolder") + listRows.getName();

        try (FileInputStream fis = new FileInputStream(dir)) {
            doc = new XWPFDocument(fis);

            // Обработка текста
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);

                    listRows.getList().forEach(it -> {
                        if (text != null && text.contains(it.textLabel().getText())) {
                            String updatedRunText = text.replace(text, it.customField().getText());
                            run.setText(updatedRunText, 0);
                        }
                    });
                }
            }

            // Обработка таблиц
            for (XWPFTable table : doc.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            List<XWPFRun> runs = paragraph.getRuns();
                            for (XWPFRun run : runs) {
                                String text = run.getText(0);

                                listRows.getList().forEach(it -> {
                                    if (text != null && text.contains(it.textLabel().getText())) {
                                        String updatedRunText = text.replace(text, it.customField().getText());
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
}
