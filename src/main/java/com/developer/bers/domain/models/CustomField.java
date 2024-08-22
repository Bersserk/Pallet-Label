package com.developer.bers.domain.models;

import com.developer.bers.domain.repositories.AppConst;
import com.developer.bers.presentation.AppProperties;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class CustomField extends JTextField {

//    String font = AppProperties.get("fontEditText");
//    int fontStyle = AppProperties.get("fontStyle");
    int fontSize = AppProperties.getNum("font_size", 20);

    public CustomField(int columns, int filter) {
        super(columns < 2 ? 20 : columns);
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        setDocumentFilter(columns);
        if(filter == AppConst.ONLY_NUMBERS){
            setOnlyNumbersFilter();
        }

    }

    private void setDocumentFilter(int maxChars) {
        AbstractDocument doc = (AbstractDocument) this.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && (fb.getDocument().getLength() + string.length() <= (maxChars))) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && (fb.getDocument().getLength() - length + text.length() <= (maxChars/1.2))) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private void setOnlyNumbersFilter() {
        AbstractDocument doc = (AbstractDocument) this.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("[0-9]+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && text.matches("[0-9]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
            }
        });
    }
}
