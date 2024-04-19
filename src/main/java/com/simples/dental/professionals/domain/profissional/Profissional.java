package com.simples.dental.professionals.domain.profissional;

import com.simples.dental.professionals.domain.AggregateRoot;
import com.simples.dental.professionals.domain.validations.ValidationHandler;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Profissional extends AggregateRoot<IdProfissional> {

    private String nome;

    private CargoProfissional cargo;

    private Instant nascimento;

    private boolean active;

    private Instant createdDate;

    protected Profissional(
            final IdProfissional profissionalID,
            final String nome,
            final CargoProfissional cargo,
            final Instant nascimento,
            final boolean active,
            final Instant createdDate
    ) {
        super(profissionalID);
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.active = active;
        this.createdDate = createdDate;
    }

    public static Profissional newProfissional(
            final String name,
            final CargoProfissional cargo,
            final Instant nascimento
    ) {
        final var id = IdProfissional.unique();
        final var now = Instant.now();
        final var active = true;
        return new Profissional(id, name, cargo, nascimento, active, now);
    }

    public Profissional update(
            final String nome,
            final CargoProfissional cargo,
            final Instant nascimento,
            final boolean active
    ) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.active = active;
        return this;
    }

    @Override
    public void validate(ValidationHandler handler) {

    }

    public Profissional deactivate() {
        this.active = false;

        return this;
    }
}
