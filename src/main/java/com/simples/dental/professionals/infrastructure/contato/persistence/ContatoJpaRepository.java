package com.simples.dental.professionals.infrastructure.contato.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoJpaRepository extends JpaRepository<ContatoJpaEntity, String> {

    default List<Object[]> selectByFields(EntityManager entityManager, List<String> fields, String q) {
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
        return nativeQuery.getResultList();
    }
}
