package com.developer.bers.data;

import com.developer.bers.presentation.surfaces.ListParagraphBuilder;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ConvertTemplateToDocument {

    private final XWPFDocument document;

    public XWPFDocument getDocument() {
        return document;
    }

    public ConvertTemplateToDocument(ListParagraphBuilder listParagraphBuilder) {
        this.document = listParagraphBuilder.getDocument();
    }

}
