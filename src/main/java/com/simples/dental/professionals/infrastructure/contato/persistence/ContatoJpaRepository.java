package com.simples.dental.professionals.infrastructure.contato.persistence;

import com.simples.dental.professionals.domain.pagination.SearchQuery;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import static com.simples.dental.professionals.infrastructure.helpers.PersistenceHelpers.mapObjectArrayToStringArray;

public interface ContatoJpaRepository extends JpaRepository<ContatoJpaEntity, String> {

    @Query(value = "SELECT * FROM contatos c WHERE c.profissional_id = %?1%", nativeQuery = true)
    List<ContatoJpaEntity> findAllByProfissionalId(String profissional_id);

    default List<String[]> selectByFields(EntityManager entityManager, SearchQuery searchQuery) {
        final var perPage = searchQuery.perPage();
        final var page = searchQuery.page();
        final var q = searchQuery.q();
        final var fields = searchQuery.fields();

        var queryString = """
                SELECT
                  %s
                FROM
                  contatos c
                WHERE
                  LOWER(c.nome) LIKE LOWER(:q)
                  OR c.contato LIKE :q
                """.formatted(String.join(", ", fields));

        jakarta.persistence.Query nativeQuery = entityManager.createNativeQuery(queryString);
        nativeQuery.setParameter("q", "%" + q + "%");
        nativeQuery.setMaxResults(perPage);
        nativeQuery.setFirstResult((page - 1) * perPage);

        final List<Object[]> rawData = nativeQuery.getResultList();
        return mapObjectArrayToStringArray(rawData);
    }
}
