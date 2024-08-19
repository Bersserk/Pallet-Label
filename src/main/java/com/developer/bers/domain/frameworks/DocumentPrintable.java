package com.developer.bers.domain.frameworks;

import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.repositories.PrintDocumentRepository;
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

    @Override
    public void printDoc(List<CustomField> listOfCustomField, XWPFDocument document, String outputPath) {

        XWPFDocument newDoc = new XWPFDocument();


        // Обновление содержимого документа
//        newTextInParagraph(listOfCustomField, newDoc, outputPath);

        // Сохранение документа
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            newDoc.write(out);
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

        // Save the updated document
//        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
//            newDoc.createParagraph().createRun().setText("This is a new paragraph.");
//
//            // Write changes to the file
//            fos.close();
//            newDoc.close();
//
//            // Important: Don't close the document manually if you're using try-with-resources
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to write document", e);
//        }
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
