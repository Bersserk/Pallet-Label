package com.developer.bers.data.store;

import com.developer.bers.domain.frameworks.StringFormatter;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalManagerBy {

    private final ResourceBundle bundle;

    public LocalManagerBy(String local) {
        this.bundle = ResourceBundle.getBundle("text", new Locale(local));
    }

    public String getLocalStringBy(String key) {
        return bundle.getString(new StringFormatter().formatedForKey(key));
    }

}
