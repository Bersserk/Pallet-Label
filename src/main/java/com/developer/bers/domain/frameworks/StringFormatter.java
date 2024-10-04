package com.developer.bers.domain.frameworks;

public class StringFormatter {

    // format to text_text_text
    public String formatedForKey(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        } else {
            StringBuilder formattedText = new StringBuilder();

            for (char currentChar : text.toCharArray()) {
                // Проверяем, является ли символ заглавной буквой
                if (Character.isUpperCase(currentChar)) {
                    // Если заглавная, добавляем '_' перед буквой и делаем ее строчной
                    if (formattedText.length() > 0) {  // избегаем подчеркивания в начале строки
                        formattedText.append('_');
                    }
                    formattedText.append(Character.toLowerCase(currentChar));
                } else if (currentChar == ' ') {
                    // Преобразуем пробелы в подчеркивания
                    formattedText.append('_');
                } else {
                    // Просто добавляем строчные буквы и символы '_'
                    formattedText.append(currentChar);
                }
            }

            return formattedText.toString();
        }
    }



    public String formatedForText(String text) {
        if (text == null || text.isEmpty()) {
            return text;  // Handle null or empty input
        } else {
            StringBuilder formattedText = new StringBuilder();

            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.charAt(i);

                if (i == 0) {
                    // Capitalize the first character
                    formattedText.append(Character.toUpperCase(currentChar));
                } else if (Character.isUpperCase(currentChar)) {
                    // Convert uppercase letters to lowercase
                    formattedText.append(Character.toLowerCase(currentChar));
                } else if (currentChar == '_') {
                    // Replace underscores with spaces
                    formattedText.append(' ');
                } else if (Character.isLetter(currentChar) || currentChar == ' ') {
                    // Include the character only if it is a letter or space
                    formattedText.append(currentChar);
                }
            }

            return formattedText.toString().trim();
        }
    }

}
