package com.developer.bers.presentation;

import com.developer.bers.data.CreatorListFromDirectory;
import com.developer.bers.data.SettingsTab;
import com.developer.bers.data.documents.DocumentManager;
import com.developer.bers.data.store.LocalManagerBy;
import com.developer.bers.domain.repositories.ListMaker;
import com.developer.bers.presentation.surfaces.BaseJFrame;
import com.developer.bers.presentation.surfaces.DataForTab;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.developer.bers.presentation.AppProperties.*;

public class MainView extends BaseJFrame {

    private final String INPUT_DIRECTORY = System.getProperty("user.dir") + "/files/input/";

    JTabbedPane tabPane;
    String[] list;
//    Map<String, XWPFDocument> listDocs;
    SettingsTab settingsTab;
    LocalManagerBy localManager;

    public MainView() throws HeadlessException {

        tabPane = new JTabbedPane();

//        ListMaker listFiles = new CreatorListFromDirectory();
//        list = listFiles.getListOf(INPUT_DIRECTORY);

        try {
            list = new DocumentManager().listDocsNames();
        } catch (Exception e) {
            // в случае если нет файлов, запустить апку с одной вкладкой настройки,
            // в которой юудет возможность добавить файлы
            throw new RuntimeException(e);
        }

        createTabs();
        createSettingTab();

        add(tabPane, BorderLayout.CENTER);
//        pack();
        fillSettingTab();

        setVisible(true);
    }


    private void createTabs() {

        Arrays.stream(list).toList().forEach(file -> {
            tabPane.addTab(file, new DataForTab(file));
        });


    }

    public void fillSettingTab(){
        Dimension d = getSize();
        int widt = d.height;
        int length = d.width;
        settingsTab.lengthOfScreen.setTextToField(String.valueOf(length));
        settingsTab.widthOfScreen.setTextToField(String.valueOf(widt));
        settingsTab.fontSize.setTextToField(String.valueOf(AppProperties.getNum(FONT_SIZE, 18)));

//        String s =

        settingsTab.dropDownLanguage.setTextToField("this");
    }

    public void fillSavedSettingTab(){
        settingsTab.lengthOfScreen.setTextToField(AppProperties.getValueByKey(LENGTH));
        settingsTab.widthOfScreen.setTextToField(AppProperties.getValueByKey(WIDT));
        settingsTab.fontSize.setTextToField(String.valueOf(AppProperties.getNum(FONT_SIZE, 18)));
        settingsTab.dropDownLanguage.setTextToField("this");
    }

    private void createSettingTab() {
        localManager = new LocalManagerBy(AppProperties.getCurrentLocal());
        settingsTab = new SettingsTab(localManager, this);

        tabPane.addTab(
                localManager.getLocalStringBy("settings"),
                settingsTab);
    }

    public void changeLanguage() {
        System.out.println("changeLanguage from MainView");
        tabPane.remove(settingsTab);
        this.createSettingTab();
    }

    public void changeProperties() {
        super.setSize(getNum("length_main_screen", 100), getNum("width_main_screen", 100));
        tabPane.setSelectedComponent(settingsTab);
    }

}
