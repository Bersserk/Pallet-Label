package com.developer.bers.data.models;

import com.developer.bers.data.documents.YamlReader;
import com.developer.bers.domain.repositories.YamlData;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.List;
import java.util.Map;

public record Paragraph(XWPFParagraph paragraph, String nameCustomLabel) {

    public String getText() {
        return paragraph().getText();
    }

    private Map<String, Object> getYamlData() {
        YamlData yamlData = new YamlReader(nameCustomLabel);
        List<Map<String, Object>> listDataByParagraphs = yamlData.getListData();

        Map<String, Object> back = null;
        for (Map<String, Object> paragraph : listDataByParagraphs) {
            String s = paragraph.get("text").toString();
            String sGet = getText();
            if (s.equals(getText())) {
                back = paragraph;
            }
        }

        return back;
    }

    public int getCharacterCountInLine() {
        Map<String, Object> o = getYamlData();
        try {
            return (int) o.get("max_chars");
        } catch (NullPointerException e) {
            return 3;
        }
    }


    public String getHint() {
        Map<String, Object> o = getYamlData();

        try {
            return o.get("hint").toString();
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String getDigitRegex() {
        Map<String, Object> o = getYamlData();
        return o.get("input_type").toString();
    }

}
