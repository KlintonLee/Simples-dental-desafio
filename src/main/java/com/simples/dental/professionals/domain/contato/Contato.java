package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.AggregateRoot;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.validations.ValidationHandler;
import lombok.Getter;
import lombok.With;

import java.time.LocalDate;

@Getter
public class Contato extends AggregateRoot<ContatoId> {

    private String nome;

    private String contato;

    private Profissional profissional;

    private LocalDate createdDate;

    protected Contato(
            final ContatoId contatoId,
            final String nome,
            final String contato,
            final Profissional profissional,
            final LocalDate createdDate
    ) {
        super(contatoId);
        this.nome = nome;
        this.contato = contato;
        this.profissional = profissional;
        this.createdDate = createdDate;
    }

    public static Contato newContato(
            String nome,
            String contato,
            Profissional profissional
    ) {
        final var id = ContatoId.unique();
        final var now = LocalDate.now();
        return new Contato(id, nome, contato, profissional, now);
    }

    public Contato update(
            final String nome,
            final String contato,
            final Profissional profissional
    ) {
        this.nome = nome;
        this.contato = contato;
        this.profissional = profissional;
        return this;
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
