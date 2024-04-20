package com.simples.dental.professionals.application.profissional.retrieve.list;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.exceptions.UnprocessableFieldsException;

import java.lang.reflect.Field;
import java.util.*;

public class DefaultListProfissionaisUseCase extends ListProfissionaisUseCase {

    private static final String IDENTIFIER_FIELD = "id";
    private static final String CREATED_DATE_FIELD = "createdDate";

    private final ProfissionalGateway profissionalGateway;

    public DefaultListProfissionaisUseCase(ProfissionalGateway profissionalGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public Pagination<Map<String, String>> execute(SearchQuery searchQuery) {
        validateFields(searchQuery);
        return this.profissionalGateway.findAll(searchQuery);
    }

    private static void validateFields(SearchQuery searchQuery) {
        List<String> allowedValues = new ArrayList<>();
        allowedValues.add(IDENTIFIER_FIELD);
        Arrays.stream(Profissional.class.getDeclaredFields()).map(Field::getName).forEach(allowedValues::add);
        allowedValues.remove(CREATED_DATE_FIELD);

        searchQuery.fields().forEach(field -> {
            if (!allowedValues.contains(field)) {
                final var availableFields = String.join(", ", allowedValues);
                final var messageError = "Os fields disponívels são: %s.".formatted(availableFields);

                throw new UnprocessableFieldsException(messageError);
            }
        });
    }
}
