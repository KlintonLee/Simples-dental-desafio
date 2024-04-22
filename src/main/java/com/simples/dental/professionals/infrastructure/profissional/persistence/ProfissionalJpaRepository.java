package com.simples.dental.professionals.infrastructure.profissional.persistence;

import com.simples.dental.professionals.domain.pagination.SearchQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.simples.dental.professionals.infrastructure.helpers.PersistenceHelpers.mapObjectArrayToStringArray;

public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalJpaEntity, String> {

    default List<String[]> selectByFields(EntityManager entityManager, SearchQuery searchQuery) {
        final var perPage = searchQuery.perPage();
        final var page = searchQuery.page();
        final var q = searchQuery.q();
        final var fields = searchQuery.fields();

        var queryString = """
                SELECT
                  %s
                FROM
                  profissionais c
                WHERE
                  c.nome LIKE :q
                  OR c.cargo LIKE :q
                  AND c.active = TRUE
                """.formatted(String.join(", ", fields));

        Query nativeQuery = entityManager.createNativeQuery(queryString);
        nativeQuery.setParameter("q", "%" + q + "%");
        nativeQuery.setMaxResults(perPage);
        nativeQuery.setFirstResult(page * perPage);

        final List<Object[]> rawData = nativeQuery.getResultList();
        return mapObjectArrayToStringArray(rawData);
    }
}
