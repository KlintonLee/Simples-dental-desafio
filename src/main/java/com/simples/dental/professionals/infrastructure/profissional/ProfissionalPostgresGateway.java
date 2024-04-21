package com.simples.dental.professionals.infrastructure.profissional;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.infrastructure.helpers.PersistenceHelpers;
import com.simples.dental.professionals.infrastructure.profissional.persistence.ProfissionalJpaEntity;
import com.simples.dental.professionals.infrastructure.profissional.persistence.ProfissionalJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class ProfissionalPostgresGateway implements ProfissionalGateway {

    private final EntityManager entityManager;

    private final ProfissionalJpaRepository repository;

    public ProfissionalPostgresGateway(EntityManager entityManager, ProfissionalJpaRepository repository) {
        this.entityManager = Objects.requireNonNull(entityManager);
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Profissional create(Profissional profissional) {
        return save(profissional);
    }

    @Override
    public Optional<Profissional> findById(IdProfissional id) {
        return this.repository
                .findById(id.getValue())
                .map(ProfissionalJpaEntity::toAggregate);
    }

    @Override
    public Profissional update(Profissional profissional) {
        return save(profissional);
    }

    @Override
    public Pagination<Map<String, String>> findAll(SearchQuery aQuery) {
        final var ramItems = repository.selectByFields(entityManager, aQuery.fields(), aQuery.q());
        final var items = PersistenceHelpers.mapFieldsWithListOfStringArray(
                aQuery.fields(),
                ramItems
        );

        return new Pagination<>(aQuery.page(), aQuery.perPage(), items.size(), items);
    }

    @Override
    public boolean existsById(IdProfissional id) {
        return this.repository.existsById(id.getValue());
    }

    private Profissional save(Profissional profissional) {
        return repository.save(ProfissionalJpaEntity.from(profissional)).toAggregate();
    }
}
