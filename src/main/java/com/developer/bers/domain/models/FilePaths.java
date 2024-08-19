package com.developer.bers.domain.models;

public record FilePaths(String inputFilePath, String outputFilePath) {
    @Override
    public String inputFilePath() {
        return inputFilePath;
    }

    @Override
    public String outputFilePath() {
        return outputFilePath;
    }
}
