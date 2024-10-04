package com.developer.bers.data;

import com.developer.bers.data.models.Paragraph;
import com.developer.bers.data.store.LocalManagerBy;
import com.developer.bers.presentation.AppProperties;
import com.developer.bers.presentation.surfaces.BaseJFrame;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LabelAndField extends JPanel implements RowComponent {

    int fieldSize;

    private final JLabel label;

    private final JTextField textField;
    private String hint;

    public void setTextToField(String text) {
        textField.setText(text);
    }


    public LabelAndField(Paragraph paragraph) {
        // TODO further some logic returning as row's component textLabel + textField
        label = new JLabel(paragraph.getText());
        hint = paragraph.getHint();

        fieldSize = AppProperties.getNum("font_size", 20);
        textField = new JTextField(fieldSize);
        textField.setFont(new Font("Arial", Font.PLAIN, fieldSize));
        setHintText();

        setFilter(paragraph);
    }

    public LabelAndField(LocalManagerBy localManager, String textLabel, int fieldLength, int fontSize, int valueByDefault) {
        // TODO further some logic returning as row's component textLabel + textField
        Font font = new Font("Arial", Font.PLAIN, fontSize);

        label = new JLabel(localManager.getLocalStringBy(textLabel));
        label.setFont(font);

        fieldSize = AppProperties.getNum("field_size", fieldLength);
        textField = new JTextField(fieldSize);
        textField.setFont(font);
        String setPref = AppProperties.getValueByKey(textLabel);
//        textField.setText(setPref);

    }


    private void setFilter(Paragraph paragraph) {
        String digit = paragraph.getDigitRegex();
        int maxChar = paragraph.getCharacterCountInLine();

        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                System.out.println("insertString");
            }


            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                System.out.println(label.getText() + "->" + "replace");
                String t = textField.getText();

                if (text.equals(hint) || t.equals(hint)) {
                    System.out.println(label.getText() + "->" + "text = " + text);

                    super.replace(fb, offset, length, text, attrs);
                }

                if (fb.getDocument().getLength() < maxChar) {
                    System.out.println(label.getText() + "->" + "length <= maxChar");
                    textField.setForeground(Color.BLACK);

                    if (!digit.equals("all")) {
                        if (text.matches(digit)) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    } else {
                        super.replace(fb, offset, length, text, attrs);

                        System.out.println(label.getText() + "->" + "after replace");
                    }
                }

            }
        });

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println(label.getText() + "->" + "focusGained");

                if (textField.getText().equals(hint)) {
                    System.out.println(label.getText() + "->" + "hintTrue");
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                } else {
                    System.out.println(label.getText() + "->" + "hintFalse");

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println(label.getText() + "->" + "focusLost");
                if (textField.getText().isEmpty()) {
                    System.out.println(label.getText() + "->" + "textField isEmpty");
                    setHintText();
                } else {
                    System.out.println(label.getText() + "->" + "textField typed");

                }
            }
        });
    }


    private void setHintText() {
        if (label.getText().equals("Today")) {
            textField.setText(getCurrentDate());
        } else {

            textField.setText(hint);
            textField.setForeground(Color.GRAY);
        }
    }

    private String getCurrentDate() {
        // Create a formatter for the desired date pattern (DD:MM:YY)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the current date and return it as a string
        return currentDate.format(formatter);
    }


    @Override
    public JComponent get() {
        this.add(label);
        this.add(textField);
        return this;
    }


    @Override
    public String getLabel() {
        return label.getText();
    }

    @Override
    public String getField() {
        if (textField.getText().equals(hint)) {
            textField.setText("");
        }

        return textField.getText();
    }

}
