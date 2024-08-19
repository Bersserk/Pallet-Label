package com.developer.bers.presentation.surfaces;

import com.developer.bers.domain.models.*;
import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.models.CustomLabel;
import com.developer.bers.domain.usecases.PrintDocumentUseCase;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {

    private XWPFDocument document;

    public MainPanel(CustomLabel nameCustomLabel) {   // принимаем имя файла для ссылки на него для создание панели на основании него
        createPanel();
        ListParagraphBuilder listParagraphBuilder = new ListParagraphBuilder(nameCustomLabel);
        addComponentsToPanel(listParagraphBuilder);

        this.document = listParagraphBuilder.getDocument();
    }

    private JPanel mainPanel;
    private GridBagConstraints gbc;

    private final int MAIN_PANEL = this.getWidth();
    private final ListTextFields <CustomLabel, CustomField> listOfTextField = new ListTextFields<>();


    private void createPanel() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setLayout(new GridBagLayout());
        this.add(mainPanel);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        mainPanel.setVisible(true);
    }

    private void addComponentsToPanel(ListParagraphBuilder listParagraphBuilder) {
        List <String> listOfParagraphs = listParagraphBuilder.getList();
        List <CustomRow> listOfCustomRows = new ArrayList<>();


        for (int i = 0; i < listOfParagraphs.size(); i++) {
            if (!listOfParagraphs.get(i).isEmpty()) {  // check if string no text we don't use it
                String textOfParagraph = listOfParagraphs.get(i);
                listOfCustomRows.add(createComponent(0, i, 2, textOfParagraph, 50));
            }
        }




        gbc.gridx = 0;
        gbc.gridy = listOfParagraphs.size() + 1;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLUE);
        emptyPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 100));
        mainPanel.add(emptyPanel, gbc);

        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.gridx = 3;
        gbc.gridy = listOfParagraphs.size() + 1;
        gbc.gridwidth = 1;

        JButton button1 = new JButton("some button 1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                new CreateNewDocumentUseCase().execute(listOfCustomRows);
            }
        });
        mainPanel.add(button1, gbc);

        gbc.insets = new Insets(0,15,0,15);
        gbc.gridx = 5;
        gbc.gridy = listOfParagraphs.size() + 1;
        gbc.gridwidth = 1;

        JButton printButton = new JButton("print");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // 1. To get data from each textField
                // 2. To check each field for validates of count's symbols
                // 3. To replace text in the form
                // 4. To take the replaced form and send it to print

                String path = listParagraphBuilder.getFilePaths().outputFilePath();




                new PrintDocumentUseCase().execute(
                        listOfCustomRows,
                        document,
                        path
                );
            }
        });
        mainPanel.add(printButton, gbc);
    }

    private CustomRow createComponent(int numRow, int numColumn, int gridWidth, String textOfLabel, int quantityColumnsForTextField) {
        gbc.gridx = numRow;
        gbc.gridy = numColumn;
        gbc.gridwidth = gridWidth;

        CustomLabel textLabel = new CustomLabel(textOfLabel);
        CustomField customField = new CustomField(quantityColumnsForTextField);
        CustomRow customRow = new CustomRow(textLabel, customField);

        listOfTextField.put(textLabel, customField);
        mainPanel.add(new RowOfTextAndField(textLabel, customField), gbc);

        return customRow;
    }

}
