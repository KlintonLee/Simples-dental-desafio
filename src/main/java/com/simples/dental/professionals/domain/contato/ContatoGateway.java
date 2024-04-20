package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;

import java.util.Map;
import java.util.Optional;

public interface ContatoGateway {

    Contato create(Contato contato);

    Optional<Contato> findById(ContatoId id);

    Contato update(Contato contato);

    Pagination<Map<String, String>> findAll(SearchQuery aQuery);

    boolean existsById(ContatoId id);

    void deleteById(ContatoId id);
}
