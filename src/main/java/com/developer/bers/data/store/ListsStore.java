package com.developer.bers.data.store;

import com.developer.bers.data.repositories.GetDropDownList;

public class ListsStore implements GetDropDownList {

    LocalManagerBy localManager;

    public ListsStore(LocalManagerBy localManager) {
        this.localManager = localManager;
    }

    @Override
    public String[] getFor(String textLabel) {

        if (textLabel.equals("language")) {
            return getLanguageList();
        } else {
            return new String[0];
        }
    }


    // Method to return a list of languages
    public String[] getLanguageList() {
        String[] list = new String[4];

        list[0] = (localManager.getLocalStringBy("polish"));
        list[1] = (localManager.getLocalStringBy("ukrainian"));
        list[2] = (localManager.getLocalStringBy("english"));
        list[3] = (localManager.getLocalStringBy("russian"));

        return list;
    }

}

