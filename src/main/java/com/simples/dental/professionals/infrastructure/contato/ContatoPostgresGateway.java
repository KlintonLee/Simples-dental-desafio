package com.simples.dental.professionals.infrastructure.contato;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.infrastructure.contato.persistence.ContatoJpaEntity;
import com.simples.dental.professionals.infrastructure.contato.persistence.ContatoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ContatoPostgresGateway implements ContatoGateway {

    private final ContatoJpaRepository repository;

    public ContatoPostgresGateway(ContatoJpaRepository repository) {
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
