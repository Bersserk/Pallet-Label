package com.developer.bers.data;

import com.developer.bers.data.documents.DocumentManager;
import com.developer.bers.data.models.Paragraph;
import com.developer.bers.domain.repositories.Components;
import com.developer.bers.domain.repositories.ListMaker;
import com.developer.bers.presentation.surfaces.ListParagraphBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentsBuilder implements Components {

    private RowComponent rowComponent;


    //    private String nameOfFile;
    private Map<GridBagConstraints, JComponent> listComponents;
    private List<Paragraph> listOfParagraphs;
    private final DocumentManager documentManager;


    public ComponentsBuilder(DocumentManager documentManager) {
        this.documentManager = documentManager;
//        listOfParagraphs = new ListParagraphBuilder(documentManager.getDocumentName()).getList();
    }

    @Override
    public Map<GridBagConstraints, JComponent> getListComponents() {
        listComponents = new HashMap<>();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;

        listOfParagraphs.forEach(paragraph -> {
            // Метод для определения типа компонента на основе текста
            if (paragraph.getText().isEmpty()) {
            } else if (paragraph.getText().contains("drop")) {
                ListMaker listMaker = new ListsForDropDownComponents();
//                rowComponent = new LabelAndDropDown(listMaker.getListOf("drop"), paragraph.getText());
            } else {
                rowComponent = new LabelAndField(paragraph);
            }

            gbc.gridy = listOfParagraphs.indexOf(paragraph);
            listComponents.put((GridBagConstraints) gbc.clone(), rowComponent.get());
        });
        return listComponents;
    }
}
