package com.developer.bers.domain.usecases;

import com.developer.bers.data.DocxFileFinder;
import com.developer.bers.data.SettingsTab;
import com.developer.bers.data.repositories.AddableTemplate;
import com.developer.bers.data.repositories.RemovableTemplate;
import com.developer.bers.domain.models.Tab;
import com.developer.bers.domain.repositories.GetListNamesFilesFromFolder;
import com.developer.bers.domain.repositories.PressButtonAddTemplate;
import com.developer.bers.domain.repositories.PressButtonRemoveTemplate;
import com.developer.bers.presentation.surfaces.DataForTab;

import javax.swing.*;
import java.util.List;

public class ManagerTabComponentsUseCase {
    private final String TARGET_DIRECTORY = System.getProperty("user.dir") + "/files/input/";


    PressButtonAddTemplate pressButtonAddTemplate = new AddableTemplate();
    PressButtonRemoveTemplate pressButtonRemoveTemplate = new RemovableTemplate();
    GetListNamesFilesFromFolder listNamesOfDirectory = new DocxFileFinder(TARGET_DIRECTORY);
    List<String> rememberedList;



    public void todo1 (){};

    public void removeTemplate (JTabbedPane tabbedPane){
        pressButtonRemoveTemplate.execute();
        checkingList(tabbedPane);
    };

    public void addTemplate (JTabbedPane tabbedPane){
        System.out.println("addTemplate from ManagerTabComponentsUseCase");
        pressButtonAddTemplate.execute();
        checkingList(tabbedPane);
    };

    private void checkingList(JTabbedPane tabPane){

        boolean repain = false;

        if(rememberedList == null){
            rememberedList = listNamesOfDirectory.getList();
            repain = true;



        } else if (listNamesOfDirectory.getList() != rememberedList){
            rememberedList = listNamesOfDirectory.getList();
            repain = true;
        }

        if(repain) {
            tabPane.removeAll();
            listNamesOfDirectory.getList().forEach(item ->
                    {
                        tabPane.addTab(item, new DataForTab(new Tab(item)));
                    }
            );
            tabPane.addTab("Settings", new SettingsTab(tabPane));

            tabPane.revalidate();
            tabPane.repaint();
        }
    }






}
