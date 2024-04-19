package com.simples.dental.professionals.application.contato.create;

public record CreateContatoCommand(
        String nome,
        String contato,
        String profissionalId
) {
    public static CreateContatoCommand with(
            final String nome,
            final String contato,
            final String profissionalId
    ) {
        return new CreateContatoCommand(nome, contato, profissionalId);
    }
}
