package com.developer.bers.helpers;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private ResourceBundle bundle;

    public LocalizationManager(Locale locale) {
        bundle = ResourceBundle.getBundle("resources/messages", locale);
    }

    public String getString(String key) {
        return bundle.getString(key);
    }

    public void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("resources/messages", locale);
    }
}

