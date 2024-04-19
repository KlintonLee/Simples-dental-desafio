package com.simples.dental.professionals.application.contato.update;

public record UpdateContatoCommand(
        String id,
        String nome,
        String contato,
        String profissionalId
) {
    public static UpdateContatoCommand with(
            final String id,
            final String nome,
            final String contato,
            final String profissionalId
    ) {
        return new UpdateContatoCommand(id, nome, contato, profissionalId);
    }
}
