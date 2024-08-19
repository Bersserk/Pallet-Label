package com.developer.bers.presentation.surfaces;

import com.developer.bers.domain.models.CustomLabel;
import com.developer.bers.domain.models.FilePaths;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListParagraphBuilder {

    private FilePaths filePaths = new FilePaths(
            "files/input/",
            "files/output/Document.docx");

    private XWPFDocument document;
    private List<String> listParagraphs;

    public XWPFDocument getDocument() {
        return document;
    }

    public FilePaths getFilePaths() {
        return filePaths;
    }
    public List<String> getList() {

        return listParagraphs;
    }



    public ListParagraphBuilder(CustomLabel nameCustomLabel) {
        try (FileInputStream fis = new FileInputStream(filePaths.inputFilePath() + nameCustomLabel.getText());
             XWPFDocument doc = new XWPFDocument(fis)) {
            document = doc;

            listParagraphs = new ArrayList<>();

            // Replace text in paragraphs

//            for (String item: list){



            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                if (text.contains("  ")) {
                    listParagraphs.addAll(splitTextByItems(text));
                } else {
                    listParagraphs.add(paragraph.getText());
                }
            }

            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            String text = paragraph.getText();
                            if (text.contains("  ")) {
                                listParagraphs.addAll(splitTextByItems(text));
                            } else {
                                listParagraphs.add(paragraph.getText());
                            }
                        }
                    }
                }
            }


            // Save the modified document
//            try (FileOutputStream fos = new FileOutputStream(filePaths.outputFilePath())) {
//                document.write(fos);
//            }

            System.out.println("Text replaced and new document saved successfully!");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List splitTextByItems(String inputString) {
//        String inputString = text;

        // Split the input string by two or more spaces
        String[] words = inputString.split("  "); // Note the double space here

        // Create a new ArrayList to store the words
        List<String> result = new ArrayList<>();

        // Add each word to the result list
        for (String word : words) {
            if (!word.trim().isEmpty()) { // Ignore empty strings (due to consecutive spaces)
                result.add(word.trim());
            }
        }

        return result;
    }
}
