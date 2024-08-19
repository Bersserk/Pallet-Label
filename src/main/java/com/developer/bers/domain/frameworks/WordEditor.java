package com.developer.bers.domain.frameworks;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.developer.bers.domain.models.FileName;
import com.developer.bers.domain.models.FilePaths;
import com.developer.bers.domain.models.ListOfFileNames;
import com.developer.bers.domain.models.CustomLabel;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordEditor {

    public static FilePaths filePaths = new FilePaths(
            "files/input/",
            "files/output/Document.docx");

    private final ListOfFileNames <FileName> listOfFileNames;

    public WordEditor(CustomLabel nameCustomLabel) {

        listOfFileNames = new ListOfFileNames<>();
//        String searchText = String.valueOf(list.get(0));
        String replaceText = "CARTAMA";
        String path = filePaths.inputFilePath() + nameCustomLabel.getText();

        try (FileInputStream fis = new FileInputStream(filePaths.inputFilePath() + nameCustomLabel.getText());
             XWPFDocument document = new XWPFDocument(fis)) {

            // Replace text in paragraphs

//            for (String item: list){

            for (XWPFParagraph paragraph : document.getParagraphs() ) {
                String text = paragraph.getText();
                if(text.contains("  ")){
//                    listOfFileNames.addAll(splitTextByItems(text));
                } else {
                    listOfFileNames.add(paragraph.getText());
                }
            }

            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            String text = paragraph.getText();
                            if (text.contains("  ")) {
//                                listOfFileNames.addAll(splitTextByItems(text));
                            } else {
                                listOfFileNames.add(paragraph.getText());
                            }
                        }
                    }
                }
            }

            // Save the modified document
            try (FileOutputStream fos = new FileOutputStream(filePaths.outputFilePath())) {
                document.write(fos);
            }

            System.out.println("Text replaced and new document saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ListOfFileNames<FileName> getListOfFileNames() {
        return listOfFileNames;
    }


    private List splitTextByItems (String inputString){
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


    private void getListTextByParagraphs(XWPFParagraph paragraph, List listText) {




        for (XWPFRun run : paragraph.getRuns()) {
            listText.add(run);

        }

        System.out.println(listText);
    }

    private void replaceTextInParagraph(XWPFParagraph paragraph, String searchText, String replaceText) {
        // Iterate through runs in paragraph
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null && text.contains(searchText)) {
                // Replace text within the run
                text = text.replace(searchText, replaceText);
                run.setText(text, 0);
            }
        }
    }

    private void newTextInParagraph(XWPFParagraph paragraph, String replaceText) {
        // Iterate through runs in paragraph
        for (XWPFRun run : paragraph.getRuns()) {
            String text = replaceText;
                run.setText(text, 0);

        }
    }
}
