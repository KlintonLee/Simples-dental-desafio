package com.simples.dental.professionals.domain.profissional;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;

import java.util.Optional;

public interface ProfissionalGateway {
    Profissional create(Profissional profissional);

    Optional<Profissional> findById(IdProfissional id);

    Profissional update(Profissional profissional);

    Pagination<Profissional> findAll(SearchQuery aQuery);
}
