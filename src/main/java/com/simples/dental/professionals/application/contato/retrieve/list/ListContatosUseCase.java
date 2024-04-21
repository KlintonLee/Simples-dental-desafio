package com.simples.dental.professionals.application.contato.retrieve.list;

import com.simples.dental.professionals.application.UseCase;
import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;

import java.util.Map;

public abstract class ListContatosUseCase
    extends UseCase<SearchQuery, Pagination<Map<String, String>>> {
}
