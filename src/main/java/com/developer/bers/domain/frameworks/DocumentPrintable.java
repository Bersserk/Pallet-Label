package com.developer.bers.domain.frameworks;

import com.developer.bers.TestMain;
import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.repositories.PrintDocumentRepository;
import com.developer.bers.helpers.CurrentWorkingDirectory;
import org.apache.poi.xwpf.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DocumentPrintable implements Printable, PrintDocumentRepository {

    XWPFDocument newDoc;

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // Печать содержимого на странице
        // textPane.printAll(g);

        return PAGE_EXISTS;
    }

    private XWPFDocument getDoc (List<CustomField> listOfCustomField){

        String str = new TestMain().getList().get(0).toString();

        XWPFDocument doc = null;
        String dir = new CurrentWorkingDirectory().getDir() + "\\files\\input\\input.docx";

        try (FileInputStream fis = new FileInputStream(dir)) {
            doc = new XWPFDocument(fis);

            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null && text.contains(str)) {
                        String updatedRunText = text.replace(text, "new Text");
                        run.setText(updatedRunText, 0);
                    }
                }
            }

            for (XWPFTable table : doc.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            String text = paragraph.getText();
                            if (text.contains("  ")) {
//                                listParagraphs.addAll(splitTextByItems(text));
                            } else {
//                                listParagraphs.add(paragraph.getText());
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

    @Override
    public void printDoc(List<CustomField> listOfCustomField, XWPFDocument document, String outputPath) {



        XWPFDocument someDoc = getDoc(listOfCustomField);


        // Создание параграфа
        XWPFParagraph paragraph = someDoc.createParagraph();

        // Добавление строки текста в параграф
        XWPFRun run = paragraph.createRun();
        run.setText("This is a line of text in the document.");


        // Обновление содержимого документа
//        newTextInParagraph(listOfCustomField, newDoc, outputPath);

        // Сохранение документа
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            someDoc.write(out);
            System.out.println("Document created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Создание и настройка принтера
        File file = new File(outputPath);
//        loadDocxFile(file);

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Printing failed: " + ex.getMessage());
            }
        }
    }

    private XWPFDocument replaceText(XWPFDocument doc, String originalText, String updatedText) {
        replaceTextInParagraphs(doc.getParagraphs(), originalText, updatedText);
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    replaceTextInParagraphs(cell.getParagraphs(), originalText, updatedText);
                }
            }
        }
        return doc;
    }

    private void replaceTextInParagraphs(List<XWPFParagraph> paragraphs, String originalText, String updatedText) {
        paragraphs.forEach(paragraph -> replaceTextInParagraph(paragraph, originalText, updatedText));
    }

    private void replaceTextInParagraph(XWPFParagraph paragraph, String originalText, String updatedText) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (XWPFRun run : runs) {
            String text = run.getText(0);
            if (text != null && text.contains(originalText)) {
                String updatedRunText = text.replace(originalText, updatedText);
                run.setText(updatedRunText, 0);
            }
        }
    }

    private void newTextInParagraph(List<CustomField> list, XWPFDocument document, String outputPath) {

        // Update the document content
        for (CustomField textField : list) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = textField.getText();
                    run.setText(text, 0);  // Replace text in the first run
                }
            }
        }
    }



    public void loadDocxFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            XWPFDocument document = new XWPFDocument(fis);
            StringBuilder htmlContent = new StringBuilder();

            // Проход по каждому элементу документа
            for (IBodyElement element : document.getBodyElements()) {
                if (element instanceof XWPFParagraph paragraph) {
                    htmlContent.append("<p>").append(paragraph.getText()).append("</p>");
                } else if (element instanceof XWPFTable table) {
                    htmlContent.append("<table border='1'>");
                    for (XWPFTableRow row : table.getRows()) {
                        htmlContent.append("<tr>");
                        for (XWPFTableCell cell : row.getTableCells()) {
                            htmlContent.append("<td>").append(cell.getText()).append("</td>");
                        }
                        htmlContent.append("</tr>");
                    }
                    htmlContent.append("</table>");
                }
            }

//            editorPane.setText(htmlContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
