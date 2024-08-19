package com.developer.bers.domain.repositories;

import com.developer.bers.domain.models.CustomField;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.event.ActionEvent;
import java.util.List;

public interface PrintDocumentRepository {
    void printDoc(List<CustomField> listOfCustomField, XWPFDocument document, String s);
}
