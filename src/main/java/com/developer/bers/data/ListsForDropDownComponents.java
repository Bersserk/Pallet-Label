package com.developer.bers.data;

import com.developer.bers.domain.repositories.ListMaker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListsForDropDownComponents implements ListMaker {
    ;

    @Override
    public List<String> getListOf(String dirPathOrData) {
        List <String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        return list;
    }
}
