package com.simples.dental.professionals.domain.pagination;

public record SearchQuery(
        int page,
        int perPage,
        String sort,
        String direction
) {

    public static SearchQuery with(
            int page,
            int perPage,
            String sort,
            String direction
    ) {
        return new SearchQuery(page, perPage, sort, direction);
    }
}
