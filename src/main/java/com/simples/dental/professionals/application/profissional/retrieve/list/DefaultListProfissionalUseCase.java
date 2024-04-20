package com.simples.dental.professionals.application.profissional.retrieve.list;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Map;
import java.util.Objects;

public class DefaultListProfissionalUseCase extends ListProfissionalUseCase{

    private final ProfissionalGateway profissionalGateway;

    public DefaultListProfissionalUseCase(ProfissionalGateway profissionalGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public Pagination<Map<String, String>> execute(SearchQuery searchQuery) {
        // Adicionar validações de fields
        return this.profissionalGateway.findAll(searchQuery);
    }
}