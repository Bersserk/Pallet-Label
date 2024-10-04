package com.developer.bers.presentation.surfaces;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListRowsBuilder<CustomRow> {
    private String nameList;
    private final List<CustomRow> listOfCustomRows;



    public ListRowsBuilder() {
        this.listOfCustomRows = new ArrayList<>();
    }


    public File getName() {
        return new File(nameList);
    }

    public List<CustomRow> getListOfCustomRows() {
        return listOfCustomRows;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
}
