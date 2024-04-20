package com.simples.dental.professionals.domain.pagination;

import java.util.List;

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
