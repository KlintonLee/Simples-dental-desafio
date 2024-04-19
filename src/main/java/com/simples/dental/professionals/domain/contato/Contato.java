package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.AggregateRoot;
import com.simples.dental.professionals.domain.validations.ValidationHandler;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Contato extends AggregateRoot<ContatoId> {

    private String nome;

    private String contato;

    private String profissionalId;

    private LocalDate createdDate;

    protected Contato(
            final ContatoId contatoId,
            final String nome,
            final String contato,
            final String profissionalId,
            final LocalDate createdDate
    ) {
        super(contatoId);
        this.nome = nome;
        this.contato = contato;
        this.profissionalId = profissionalId;
        this.createdDate = createdDate;
    }

    public static Contato newContato(
            String nome,
            String contato,
            String profissionalId
    ) {
        final var id = ContatoId.unique();
        final var now = LocalDate.now();
        return new Contato(id, nome, contato, profissionalId, now);
    }

    public Contato update(
            final String nome,
            final String contato,
            final String profissionalId
    ) {
        this.nome = nome;
        this.contato = contato;
        this.profissionalId = profissionalId;
        return this;
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
