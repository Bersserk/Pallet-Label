package com.developer.bers.domain.frameworks;

public class StringFormatter {

    public String formatedForKey(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        } else {

            StringBuilder formattedText = new StringBuilder();

            for (char currentChar : text.toCharArray()) {
                if (Character.isLetter(currentChar) || currentChar == ' ' || currentChar == '_' ) {
                    if (currentChar == ' ') {
                        formattedText.append('_');
                    } else if (currentChar == '_'){
                        formattedText.append(currentChar);
                    }
                    else {
                        formattedText.append(Character.toLowerCase(currentChar));
                    }
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
