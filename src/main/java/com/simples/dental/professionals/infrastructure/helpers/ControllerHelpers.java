package com.simples.dental.professionals.infrastructure.helpers;

import com.simples.dental.professionals.domain.exceptions.InvalidPaginationValuesException;
import com.simples.dental.professionals.domain.exceptions.UnprocessableEntityException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerHelpers {

    public static LocalDate formatDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new UnprocessableEntityException("O formato da data deve ser dd-MM-yyyy, por exemplo: 15-02-2000");
        }
    }

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

    public static void validatePaginationValues(int page, int perPage) {
        if (page <= 0) {
            throw new InvalidPaginationValuesException("A página não pode ser zero ou negativo");
        }
        if (perPage < 0) {
            throw new InvalidPaginationValuesException("Os resultados por página não deve ser negativo");
        }
    }
}
