package com.developer.bers.domain.usecases;

import com.developer.bers.domain.frameworks.DocumentPrintable;
import com.developer.bers.domain.frameworks.DocumentSaveable;
import com.developer.bers.domain.repositories.PrintNewDocumentRepository;
import com.developer.bers.domain.repositories.SaveNewDocumentRepository;
import com.developer.bers.presentation.surfaces.CustomRow;
import com.developer.bers.presentation.surfaces.ListRows;

import java.util.List;

public class MakeNewDocumentUseCase {

    PrintNewDocumentRepository printNewDocumentRepository = new DocumentPrintable();
    SaveNewDocumentRepository saveNewDocumentRepository = new DocumentSaveable();

    public void execute(ListRows<CustomRow> listRows) {
        saveNewDocumentRepository.saveDoc(listRows);
        printNewDocumentRepository.printDoc(listRows.getName());
    }


}
