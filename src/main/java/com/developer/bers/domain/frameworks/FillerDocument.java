package com.developer.bers.domain.frameworks;

import com.developer.bers.data.RowComponent;
import org.apache.poi.xwpf.usermodel.*;

import java.util.List;
import java.util.Map;

public class FillerDocument {
    private final XWPFDocument document;
    private final Map<String, String> listRows;

    public FillerDocument(XWPFDocument document, Map<String, String> listRows) {
        this.document = document;
        this.listRows = listRows;
    }

    public XWPFDocument getFilledDoc() {

        return runByList(document, listRows);
    }

    private XWPFDocument runByList(XWPFDocument document, Map<String, String> listRows) {

        List<XWPFParagraph> paragraphs = document.getParagraphs();

        StringBuilder combainedText = new StringBuilder();
        paragraphs.forEach(paragraph -> {

            paragraph.getRuns().forEach(run -> {
                String textRun = run.text();
                combainedText.append(textRun);

                String returnedText = listRowsContain(combainedText.toString(), listRows);
                if (returnedText != null) {
                    run.setText(returnedText + " ", 0);
                    combainedText.setLength(0);
                } else {
                    run.setText("", 0);
                }
            });
        });

        // Обработка таблиц
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {

                            String textRun = run.text();
                            combainedText.append(textRun);

                            String returnedText = listRowsContain(combainedText.toString(), listRows);
                            if (returnedText != null) {
                                run.setText(returnedText + " ", 0);
                                combainedText.setLength(0);
                            } else {
                                run.setText("", 0);
                            }
                        }
                    }
                }
            }
        }

        return document;
    }

    private String listRowsContain(String textRun, Map<String, String> listRows) {

        String textReturn = null;

        // Обход всех ключей в HashMap
        for (String key : listRows.keySet()) {
            if (textRun.contains(key)&& !key.isEmpty()) {
                textReturn = listRows.get(key);
                break;
            }
        }

        return textReturn;
    }
}
