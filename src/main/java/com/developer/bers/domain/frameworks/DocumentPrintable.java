package com.developer.bers.domain.frameworks;

import com.developer.bers.domain.repositories.AppConst;
import com.developer.bers.domain.repositories.PrintNewDocumentRepository;
import com.developer.bers.presentation.AppProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

public class DocumentPrintable implements Printable, PrintNewDocumentRepository {

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        return PAGE_EXISTS;
    }

    @Override
    public void printDoc(String nameOfFile) {
        if (new File(AppProperties.get(AppConst.OUTPUT_FOLDER) + nameOfFile).exists()) {
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
        } else {
            JOptionPane.showMessageDialog(null, "Suitable file not found. Printing impossible");
        }

    }
}
