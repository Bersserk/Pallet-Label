package com.developer.bers.presentation.surfaces;

import java.util.ArrayList;
import java.util.List;

public class ListRows <CustomRow> {
    private String nameList;
    private List<CustomRow> listOfCustomRows;



    public ListRows() {
        this.listOfCustomRows = new ArrayList<>();
    }


    public String getName() {
        return nameList;
    }

    public List<CustomRow> getList() {
        return listOfCustomRows;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }
}
