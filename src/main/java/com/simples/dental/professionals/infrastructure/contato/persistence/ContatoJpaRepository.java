package com.simples.dental.professionals.infrastructure.contato.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.simples.dental.professionals.infrastructure.helpers.PersistenceHelpers.mapObjectArrayToStringArray;

public interface ContatoJpaRepository extends JpaRepository<ContatoJpaEntity, String> {

    default List<String[]> selectByFields(EntityManager entityManager, List<String> fields, String q) {
        String queryString = """
                SELECT
                  %s
                FROM
                  contatos c
                WHERE
                  c.nome LIKE :q
                  OR c.contato LIKE :q
                """.formatted(String.join(", ", fields));
        Query nativeQuery = entityManager.createNativeQuery(queryString);
        nativeQuery.setParameter("q", "%" + q + "%");

        final List<Object[]> rawData = nativeQuery.getResultList();
        return mapObjectArrayToStringArray(rawData);
    }
}
