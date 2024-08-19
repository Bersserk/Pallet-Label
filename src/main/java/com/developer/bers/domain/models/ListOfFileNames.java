package com.developer.bers.domain.models;

import java.awt.*;
import java.util.ArrayList;

public class ListOfFileNames <FileName> extends List {
    public java.util.List<FileName> getList() {
        return list;
    }

    private java.util.List<FileName> list = new ArrayList<>();

}
