package com.developer.bers.domain.usecases;

import com.developer.bers.domain.frameworks.DocumentPrintable;
import com.developer.bers.domain.repositories.PrintDocumentRepository;
import com.developer.bers.presentation.surfaces.CustomRow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.List;

public class PrintDocumentUseCase {

    PrintDocumentRepository printDocumentRepository = new DocumentPrintable();
//    PrintDocumentRepository printDocumentRepository = new DocxPrinter();

    public void execute(List<CustomRow> listOfCustomField, XWPFDocument document, String s) {
        printDocumentRepository.printDoc(listOfCustomField, document, s);
    }


}
