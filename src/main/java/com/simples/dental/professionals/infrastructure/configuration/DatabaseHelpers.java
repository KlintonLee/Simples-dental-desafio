package com.simples.dental.professionals.infrastructure.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelpers {

    public static List<String[]> mapObjectArrayToStringArray(List<Object[]> rawData) {
        List<String[]> result = new ArrayList<>();

        rawData.forEach(raw -> {
            StringBuilder sb = new StringBuilder();
            for (Object obj : raw) {
                if (obj != null) {
                    sb.append(obj.toString()).append(" ");
                }
            }
            result.add(sb.toString().trim().split(" "));
        });

        return result;
    }

    public static List<Map<String, String>> mapFieldsWithListOfStringArray(List<String> fields, List<String[]> rawInput) {
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        rawInput.forEach(raw -> {
            for (int i = 0; i < fields.size(); i++) {
                map.put(fields.get(i), raw[i]);
            }
        });
        result.add(map);

        return result;
    }

}
