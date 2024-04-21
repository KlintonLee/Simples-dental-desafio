package com.simples.dental.professionals.infrastructure.profissional.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.simples.dental.professionals.infrastructure.helpers.PersistenceHelpers.mapObjectArrayToStringArray;

public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalJpaEntity, String> {

    default List<String[]> selectByFields(EntityManager entityManager, List<String> fields, String q) {
        String queryString = """
                SELECT
                  %s
                FROM
                  profissionais c
                WHERE
                  c.nome LIKE :q
                  OR c.cargo LIKE :q
                """.formatted(String.join(", ", fields));
        Query nativeQuery = entityManager.createNativeQuery(queryString);
        nativeQuery.setParameter("q", "%" + q + "%");

        final List<Object[]> rawData = nativeQuery.getResultList();
        return mapObjectArrayToStringArray(rawData);
    }
}
