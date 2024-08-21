package com.developer.bers.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    public static String get(String key) {
        if (properties.containsKey(key)) {
            System.out.println("Current dir = " + System.getProperty("user.dir") + properties.getProperty(key));
            return System.getProperty("user.dir") + properties.getProperty(key);
        } else {
            throw new IllegalArgumentException("No value for the key: " + key);
        }
    }
}
