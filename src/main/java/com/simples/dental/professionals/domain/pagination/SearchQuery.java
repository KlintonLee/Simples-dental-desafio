package com.simples.dental.professionals.domain.pagination;

import lombok.With;

import java.util.List;

@With
public record SearchQuery(
        int page,
        int perPage,
        String q,
        List<String> fields
) {

    public static SearchQuery with(
            int page,
            int perPage,
            String q,
            List<String> fields
    ) {
        return new SearchQuery(page, perPage, q, fields);
    }
}
