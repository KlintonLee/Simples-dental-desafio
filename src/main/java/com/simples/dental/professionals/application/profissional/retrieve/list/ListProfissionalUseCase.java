package com.simples.dental.professionals.application.profissional.retrieve.list;

import com.simples.dental.professionals.application.UseCase;
import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;

import java.util.Map;

public abstract class ListProfissionalUseCase
    extends UseCase<SearchQuery, Pagination<Map<String, String>>> {
}
