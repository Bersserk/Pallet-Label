package com.developer.bers.presentation;

import com.developer.bers.data.store.ListsStore;
import com.developer.bers.data.store.LocalManagerBy;
import com.developer.bers.domain.frameworks.StringFormatter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AppProperties {

    private static final Properties properties = new Properties();
//    private static ResourceBundle bundle;

    private static final String SETTINGS_FILE = AppProperties.get("properties");
    private static final String LOCAL = "local";
    public static final String LENGTH = "length_main_screen";
    public static final String WIDT = "width_main_screen";
    public static final String FONT_SIZE = "font_size";
    public static final String APPLICATION_SETTINGS = "Application Settings";

    static {
        try (InputStream input = AppProperties.class.getClassLoader().getResourceAsStream("settings.properties")) {
            if (input == null) {
                throw new IllegalArgumentException("Unable to find settings.properties");
            }
            properties.load(input);

            LocalManagerBy localManager = new LocalManagerBy(properties.getProperty(LOCAL));

        } catch (IOException ex) {
            throw new ExceptionInInitializerError("Failed to load properties file: " + ex.getMessage());
        }
    }

    private AppProperties() {
        // Private constructor to prevent instantiation
    }

    public static String get(String inputKey) {
        String key = new StringFormatter().formatedForKey(inputKey);

        if (key.equals("properties")) {
            return System.getProperty("user.dir") + "\\src\\main\\resources\\settings.properties";
        } else if (properties.containsKey(key)) {
            String s1 = System.getProperty("user.dir");
            String s2 = properties.getProperty(key);
            return System.getProperty("user.dir") + properties.getProperty(key);
        } else {
            throw new IllegalArgumentException("No value for the key: " + key);
        }
    }

    public static String getValueByKey(String key){
        String formatedKey = new StringFormatter().formatedForKey(key);
        return properties.getProperty(formatedKey);
    }

    public static int getNum(String inputKey, int byDefault) {
        String key = new StringFormatter().formatedForKey(inputKey);
        if (properties.containsKey(key)) {
            try {
                return parseInt(properties.getProperty(key));
            } catch (NumberFormatException e) {
                return byDefault;
            }
        } else {
            return byDefault;
        }
    }


    public static void setProperty(String key, String text) {
        properties.setProperty(key, text);
        store();
    }

    public static void store() {
        try {
            properties.store(new FileOutputStream(SETTINGS_FILE), APPLICATION_SETTINGS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load(FileInputStream in) {
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCurrentLocal() {
        return properties.getProperty(LOCAL);
    }
}
