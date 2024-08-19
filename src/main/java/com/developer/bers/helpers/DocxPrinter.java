package com.developer.bers.helpers;

import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.repositories.PrintDocumentRepository;
import com.developer.bers.presentation.surfaces.CustomRow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DocxPrinter extends JFrame implements ActionListener, Printable, PrintDocumentRepository {
//    private JTextPane textPane;

    public DocxPrinter() {
        // Настройка интерфейса
//        setTitle("Docx Printer");
//        setSize(600, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        textPane = new JTextPane();
//        textPane.setEditable(false);
//
//        JScrollPane scrollPane = new JScrollPane(textPane);
//        add(scrollPane, BorderLayout.CENTER);
//
//        JButton printButton = new JButton("Print");
//        printButton.addActionListener(this);
//        add(printButton, BorderLayout.SOUTH);
//
//        setVisible(true);
    }

    // Метод для чтения содержимого .docx файла
    public void loadDocxFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            XWPFDocument document = new XWPFDocument(fis);
//            StringBuilder text = new StringBuilder();
//
//            for (XWPFParagraph paragraph : document.getParagraphs()) {
//                text.append(paragraph.getText()).append("\n");
//            }
//
//            textPane.setText(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод обработки нажатия на кнопку "Print"
    @Override
    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Метод для реализации Printable интерфейса
    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

//        textPane.printAll(g);

        return PAGE_EXISTS;
    }

    // Метод запуска программы
//    public static void main(String[] args) {
//        DocxPrinter printer = new DocxPrinter();
//        File file = new File("example.docx");  // Укажите путь к вашему .docx файлу
//        printer.loadDocxFile(file);
//    }

    @Override
    public void printDoc(List<CustomRow> listOfCustomField, XWPFDocument document, String s){
        DocxPrinter printer = new DocxPrinter();
        String path = System.getProperty("user.dir");
        File file = new File(path + "/files/output/Document.docx");  // Укажите путь к вашему .docx файлу
        printer.loadDocxFile(file);

    }

}
