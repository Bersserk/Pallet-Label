package com.developer.bers.presentation.surfaces;

import com.developer.bers.data.CustomButton;
import com.developer.bers.data.ComponentsBuilder;
import com.developer.bers.data.RowComponent;
import com.developer.bers.data.documents.DocumentManager;
import com.developer.bers.domain.frameworks.DocumentPrintable;
import com.developer.bers.domain.repositories.Components;
import com.developer.bers.domain.repositories.PrintNewDocumentRepository;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DataForTab extends JPanel {

    private JPanel mainPanel;
    private GridBagConstraints gbc;
    private DocumentManager documentManager;

    public DataForTab(String file) {
//        documentManager = new DocumentManager(file);
        createPanel();
        addComponentsToPanel();

    }

    private void createPanel() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setLayout(new GridBagLayout());
        this.add(mainPanel);
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        mainPanel.setVisible(true);
    }


    private void addComponentsToPanel() {

        Components components = new ComponentsBuilder(documentManager);
        Map <GridBagConstraints, JComponent> listComponents = components.getListComponents();

        listComponents.forEach( (gbc, component) -> {
            mainPanel.add(component, gbc);
        });

        addCustomButton(listComponents, "Print");

    }

    private void addCustomButton(Map<GridBagConstraints, JComponent> listComponents, String textOfButton) {


        CustomButton buttonComponent = new CustomButton(textOfButton, () -> {
            // TODO Here code for execute by button pressing
            System.out.println("button is pressed");

            // 1. getValuesFromTextFields ()
            Map<String, String> listValuesFromTextFields = new HashMap<>();

            // Обход всех значений (value) в HashMap
            for (Map.Entry<GridBagConstraints, JComponent> entry : listComponents.entrySet()) {
                // Приведение значения к RowComponent
                if (entry.getValue() instanceof RowComponent) {
                    RowComponent component = (RowComponent) entry.getValue();

                    // Используем методы RowComponent для получения данных
                    listValuesFromTextFields.put(
                            component.getLabel(),
                            component.getField()
                    );
                }
            }

            documentManager.fillTemplateBy (listValuesFromTextFields);
            PrintNewDocumentRepository print = new DocumentPrintable();
//            print.printDoc(documentManager.getFile());

        });
        mainPanel.add(buttonComponent, buttonComponent.getGbc(listComponents.size()));
    }


}
