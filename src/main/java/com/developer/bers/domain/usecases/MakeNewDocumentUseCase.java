package com.developer.bers.domain.usecases;

import com.developer.bers.domain.frameworks.DocumentPrintable;
import com.developer.bers.domain.frameworks.DocumentSaveable;
import com.developer.bers.domain.repositories.PrintNewDocumentRepository;
import com.developer.bers.domain.repositories.SaveNewDocumentRepository;
import com.developer.bers.data.RowComponent;
import com.developer.bers.presentation.surfaces.ListRowsBuilder;

public class MakeNewDocumentUseCase {

    PrintNewDocumentRepository printNewDocumentRepository = new DocumentPrintable();
//    SaveNewDocumentRepository saveNewDocumentRepository = new DocumentSaveable();
//
    public void execute(ListRowsBuilder<RowComponent> listRows) {
//        saveNewDocumentRepository.saveDoc(listRows);
        printNewDocumentRepository.printDoc(listRows.getName());
    }


}
