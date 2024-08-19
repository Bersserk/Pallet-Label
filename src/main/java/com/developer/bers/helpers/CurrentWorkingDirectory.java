package com.developer.bers.helpers;

// Класс для показывания в консоли текущий рабочей директории программы

public class CurrentWorkingDirectory {
    public String getDir() {
        return System.getProperty("user.dir");
    }
}
