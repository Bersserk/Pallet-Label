package com.developer.bers.domain.usecases;

import com.developer.bers.domain.frameworks.DocumentPrintable;
import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.repositories.PrintDocumentRepository;
import com.developer.bers.helpers.DocxPrinter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.event.ActionEvent;
import java.util.List;

public class PrintDocumentUseCase {

    PrintDocumentRepository printDocumentRepository = new DocumentPrintable();
//    PrintDocumentRepository printDocumentRepository = new DocxPrinter();

    public void execute(List<CustomField> listOfCustomField, XWPFDocument document, String s) {
        printDocumentRepository.printDoc(listOfCustomField, document, s);
    }


}
