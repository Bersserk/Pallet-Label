package com.developer.bers.domain.usecases;

import com.developer.bers.data.CreatorListFromDirectory;
import com.developer.bers.data.repositories.AddableTemplate;
import com.developer.bers.data.repositories.RemovableTemplate;
import com.developer.bers.domain.repositories.ListMaker;
import com.developer.bers.domain.repositories.PressButtonAddTemplate;
import com.developer.bers.domain.repositories.PressButtonRemoveTemplate;

import java.util.List;

public class ManagerTabComponentsUseCase {
    private final String TARGET_DIRECTORY = System.getProperty("user.dir") + "/files/input/";


    PressButtonAddTemplate pressButtonAddTemplate = new AddableTemplate();
    PressButtonRemoveTemplate pressButtonRemoveTemplate = new RemovableTemplate();
    ListMaker listNamesOfDirectory = new CreatorListFromDirectory();
    List<String> rememberedList;




//    public void removeTemplate (JTabbedPane tabbedPane){
//        pressButtonRemoveTemplate.execute();
//        checkingList(tabbedPane);
//    };
//
//    public void addTemplate (JTabbedPane tabbedPane){
//        System.out.println("addTemplate from ManagerTabComponentsUseCase");
//        pressButtonAddTemplate.execute();
//        checkingList(tabbedPane);
//    };

//    private void checkingList(JTabbedPane tabPane){
//
//        boolean repain = false;
//
//        if(rememberedList == null){
//            rememberedList = listNamesOfDirectory.getListOf();
//            repain = true;
//
//
//
//        } else if (listNamesOfDirectory.getListOf() != rememberedList){
//            rememberedList = listNamesOfDirectory.getListOf();
//            repain = true;
//        }
//
//        if(repain) {
//            tabPane.removeAll();
//            listNamesOfDirectory.getListOf().forEach(item ->
//                    {
////                        tabPane.addTab(item, new DataForTab(item, this));
//                    }
//            );
//            tabPane.addTab("Settings", new SettingsTab(tabPane));
//
//            tabPane.revalidate();
//            tabPane.repaint();
//        }
//    }






}
