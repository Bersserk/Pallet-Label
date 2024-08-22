package com.developer.bers.presentation;

import com.developer.bers.domain.frameworks.StringFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class AppProperties {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = AppProperties.class.getClassLoader().getResourceAsStream("settings.properties")) {

            if (input == null) {
                throw new IllegalArgumentException("Unable to find settings.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new ExceptionInInitializerError("Failed to load properties file: " + ex.getMessage());
        }
    }

    private AppProperties() {
        // Приватный конструктор для предотвращения создания экземпляров
    }

    public static String get(String inputKey) {

        String key = new StringFormatter().formatedForKey(inputKey);

        if (key.equals("properties")) {
            return System.getProperty("user.dir") + "\\src\\main\\resources\\settings.properties";
        } else if (properties.containsKey(key)) {
            return System.getProperty("user.dir") + properties.getProperty(key);
        } else {
            throw new IllegalArgumentException("No value for the key: " + key);
        }
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
}
