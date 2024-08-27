package com.developer.bers.presentation.surfaces;

import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.models.Tab;
import com.developer.bers.domain.repositories.AppConst;
import com.developer.bers.domain.usecases.MakeNewDocumentUseCase;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DataForTab extends JPanel {

    private final XWPFDocument document;

    // принимаем имя файла для ссылки на него для создание панели на основании него
    public DataForTab(Tab nameOfFile) {
        createPanel();
        ListParagraphBuilder listParagraphBuilder = new ListParagraphBuilder(nameOfFile);
        addComponentsToPanel(listParagraphBuilder, nameOfFile);

        this.document = listParagraphBuilder.getDocument();
    }

    private JPanel mainPanel;
    private GridBagConstraints gbc;


    private void addComponentsToPanel(ListParagraphBuilder listParagraphBuilder, Tab nameOfFile) {
        List <String> listOfParagraphs = listParagraphBuilder.getList();
        ListRows <CustomRow> listOfCustomRows = new ListRows<>();
        listOfCustomRows.setNameList(nameOfFile.getText());


        for (int i = 0; i < listOfParagraphs.size(); i++) {
            if (!listOfParagraphs.get(i).isEmpty()) {  // check if string no text we don't use it
                String textOfParagraph = listOfParagraphs.get(i);
                int num = super.getWidth();
                listOfCustomRows.getListOfCustomRows().add(createComponent(0, i, 2, textOfParagraph, 25));
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
                //  TODO
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

                new MakeNewDocumentUseCase().execute(listOfCustomRows);
            }
        });
        mainPanel.add(printButton, gbc);
    }

    private CustomRow createComponent(
            int numRow,
            int numColumn,
            int gridWidth,
            String textOfLabel,
            int quantityColumnsForTextField) {

        gbc.gridx = numRow;
        gbc.gridy = numColumn;
        gbc.gridwidth = gridWidth;

        Tab textLabel = new Tab(textOfLabel);
        CustomField customField = new CustomField(quantityColumnsForTextField, AppConst.NON_FILTER);

        CustomRow customRow = new CustomRow(textLabel, customField);

        mainPanel.add(new RowOfTextAndField(textLabel, customField), gbc);

        return customRow;
    }

    private void createPanel() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setLayout(new GridBagLayout());
        this.add(mainPanel);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        mainPanel.setVisible(true);
    }

}
