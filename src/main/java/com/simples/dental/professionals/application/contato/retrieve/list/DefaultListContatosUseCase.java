package com.simples.dental.professionals.application.contato.retrieve.list;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.simples.dental.professionals.application.Helpers.validateFields;

public class DefaultListContatosUseCase extends ListContatosUseCase {

    private final ContatoGateway contatoGateway;

    public DefaultListContatosUseCase(ContatoGateway contatoGateway) {
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
    }

    @Override
    public Pagination<Map<String, String>> execute(SearchQuery searchQuery) {
        validateFields(Contato.class, searchQuery);

        final var cloneFields = new ArrayList<>(searchQuery.fields());
        cloneFields.replaceAll(mapProfissionalField());
        final var mappedSearchQuery = searchQuery.withFields(cloneFields);

        return this.contatoGateway.findAll(mappedSearchQuery);
    }

    private static UnaryOperator<String> mapProfissionalField() {
        return field -> field.equals("profissional") ? "profissional_id" : field;
    }
}
