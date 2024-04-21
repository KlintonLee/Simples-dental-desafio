package com.simples.dental.professionals.application;

import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.exceptions.UnprocessableFieldsException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Helpers {

    private static final String IDENTIFIER_FIELD = "id";

    public static <T> void validateFields(Class<T> clazz, SearchQuery searchQuery) {
        List<String> allowedValues = new ArrayList<>();
        allowedValues.add(IDENTIFIER_FIELD);
        Arrays.stream(clazz.getDeclaredFields())
                .map(camelToSnakeCase())
                .forEach(allowedValues::add);

        searchQuery.fields().forEach(field -> {
            if (!allowedValues.contains(field)) {
                final var availableFields = String.join(", ", allowedValues);
                final var messageError = "Os fields disponívels são: %s.".formatted(availableFields);

                throw new UnprocessableFieldsException(messageError);
            }
        });
    }

    private static Function<Field, String> camelToSnakeCase() {
        return field -> field.getName()
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }
}
