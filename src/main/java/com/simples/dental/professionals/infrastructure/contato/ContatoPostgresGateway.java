package com.simples.dental.professionals.infrastructure.contato;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.infrastructure.configuration.DatabaseHelpers;
import com.simples.dental.professionals.infrastructure.contato.persistence.ContatoJpaEntity;
import com.simples.dental.professionals.infrastructure.contato.persistence.ContatoJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class ContatoPostgresGateway implements ContatoGateway {

    private final EntityManager entityManager;

    private final ContatoJpaRepository repository;

    public ContatoPostgresGateway(EntityManager entityManager, ContatoJpaRepository repository) {
        this.entityManager = Objects.requireNonNull(entityManager);
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Contato create(Contato contato) {
        return save(contato);
    }

    @Override
    public Optional<Contato> findById(ContatoId id) {
        return this.repository.findById(id.getValue()).map(ContatoJpaEntity::toAggregate);
    }

    @Override
    public Contato update(Contato contato) {
        return save(contato);
    }

    @Override
    public Pagination<Map<String, String>> findAll(SearchQuery aQuery) {
        final var rawItems = repository.selectByFields(entityManager, aQuery.fields(), aQuery.q());
        final var items = DatabaseHelpers.mapFieldsWithListOfStringArray(
                aQuery.fields(),
                rawItems
        );

        return new Pagination<>(aQuery.page(), aQuery.perPage(), items.size(), items);
    }

    @Override
    public boolean existsById(ContatoId id) {
        return this.repository.existsById(id.getValue());
    }

    @Override
    public void deleteById(ContatoId id) {
        this.repository.deleteById(id.getValue());
    }

    private Contato save(Contato contato) {
        return this.repository.save(ContatoJpaEntity.from(contato)).toAggregate();
    }
}
