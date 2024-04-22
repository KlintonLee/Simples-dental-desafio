package com.simples.dental.professionals.domain.profissional;

import com.simples.dental.professionals.domain.AggregateRoot;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Profissional extends AggregateRoot<IdProfissional> {

    private String nome;

    private CargoProfissional cargo;

    private LocalDate nascimento;

    private boolean active;

    private LocalDate createdDate;

    protected Profissional(
            final IdProfissional profissionalID,
            final String nome,
            final CargoProfissional cargo,
            final LocalDate nascimento,
            final boolean active,
            final LocalDate createdDate
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
            final LocalDate nascimento
    ) {
        final var id = IdProfissional.unique();
        final var now = LocalDate.now();
        final var active = true;
        return new Profissional(id, name, cargo, nascimento, active, now);
    }

    public static Profissional with(
            final String id,
            final String nome,
            final CargoProfissional cargo,
            final LocalDate nascimento,
            final boolean active,
            final LocalDate createdDate
    ) {
        return new Profissional(
                IdProfissional.from(id),
                nome,
                cargo,
                nascimento,
                active,
                createdDate
        );
    }

    public Profissional update(
            final String nome,
            final CargoProfissional cargo,
            final LocalDate nascimento,
            final boolean active
    ) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.active = active;
        return this;
    }

    @Override
    public void validate() {

    }

    public Profissional deactivate() {
        this.active = false;

        return this;
    }
}
