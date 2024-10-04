package com.developer.bers.data.documents;

import com.developer.bers.domain.repositories.YamlData;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YamlReader implements YamlData {

    private final List<Map<String, Object>> listData;

    public YamlReader(String document){
        // Чтение файла YAML из ресурсов

        InputStream inputStream = YamlReader.class
                .getClassLoader()
                .getResourceAsStream("documents.yaml");

        // Создание объекта Yaml для парсинга
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        // Доступ к данным

        String s = document.toString();
        listData = (List<Map<String, Object>>) ((Map<String, Object>) data.get("documents")).get(document);

        // Пример доступа к строкам документа
//        for (Map<String, Object> line : listData) {
//            System.out.println("Text: " + line.get("text"));
//            System.out.println("Font Size: " + line.get("font_size"));
//            System.out.println("Max Chars: " + line.get("max_chars"));
//            System.out.println("Input Type: " + line.get("input_type"));
//            System.out.println("---------------------------");
//        }
    }

    @Override
    public List<Map<String, Object>> getListData() {

        return listData;
    }
}

