package com.simples.dental.professionals.application.contato.create;

import com.simples.dental.professionals.domain.contato.Contato;

import java.time.LocalDate;

public record CreateContatoOutput(
        String id,
        String nome,
        String contato,
        String profissionalId,
        LocalDate createdDate
) {
    public static CreateContatoOutput from(Contato aContato) {
        return new CreateContatoOutput(
                aContato.getId().getValue(),
                aContato.getNome(),
                aContato.getContato(),
                aContato.getProfissionalId(),
                aContato.getCreatedDate()
        );
    }
}
