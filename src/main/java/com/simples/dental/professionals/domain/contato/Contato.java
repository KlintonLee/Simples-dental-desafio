package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.AggregateRoot;
import com.simples.dental.professionals.domain.profissional.Profissional;
import lombok.Getter;

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

    public static Contato with(
            final ContatoId contatoId,
            final String nome,
            final String contato,
            final Profissional profissional,
            final LocalDate createdDate
    ) {
        return new Contato(
                contatoId,
                nome,
                contato,
                profissional,
                createdDate
        );
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
    public void validate() {
        new ContatoValidator(this).validate();
    }
}
