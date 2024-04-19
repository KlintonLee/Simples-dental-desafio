package com.simples.dental.professionals.infrastructure.profissional;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.infrastructure.profissional.persistence.ProfissionalJpaEntity;
import com.simples.dental.professionals.infrastructure.profissional.persistence.ProfissionalJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ProfissionalPostgresGateway implements ProfissionalGateway {

    private final ProfissionalJpaRepository repository;

    public ProfissionalPostgresGateway(ProfissionalJpaRepository repository) {
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
    public Pagination<Profissional> findAll(SearchQuery aQuery) {
        return null;
    }

    private Profissional save(Profissional profissional) {
        return repository.save(ProfissionalJpaEntity.from(profissional)).toAggregate();
    }
}
