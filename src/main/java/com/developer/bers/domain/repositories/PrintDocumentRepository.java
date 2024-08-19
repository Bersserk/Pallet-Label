package com.developer.bers.domain.repositories;

import com.developer.bers.domain.models.CustomField;
import com.developer.bers.presentation.surfaces.CustomRow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.event.ActionEvent;
import java.util.List;

public interface PrintDocumentRepository {
    void printDoc(List <CustomRow> listOfCustomRows, XWPFDocument document, String s);
}
