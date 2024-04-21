package com.simples.dental.professionals.infrastructure.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerHelpers {

    public static <T> List<String> fieldsMapper(Class<T> clazz, List<String> fields) {
        if (fields == null) {
            fields = Arrays
                    .stream(clazz.getDeclaredFields())
                    .map(field -> camelToSnakeCase(field.getName()))
                    .collect(Collectors.toList());
        }
        if (!fields.contains("id")) {
            fields.add("id");
        }
        return fields;
    }

    private static String camelToSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

}
