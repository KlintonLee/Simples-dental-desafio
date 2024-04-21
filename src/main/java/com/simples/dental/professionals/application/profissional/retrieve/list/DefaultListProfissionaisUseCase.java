package com.simples.dental.professionals.application.profissional.retrieve.list;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import java.util.*;

import static com.simples.dental.professionals.application.Helpers.validateFields;

public class DefaultListProfissionaisUseCase extends ListProfissionaisUseCase {

    private final ProfissionalGateway profissionalGateway;

    public DefaultListProfissionaisUseCase(ProfissionalGateway profissionalGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public Pagination<Map<String, String>> execute(SearchQuery searchQuery) {
        validateFields(Profissional.class, searchQuery);

        return this.profissionalGateway.findAll(searchQuery);
    }
}
