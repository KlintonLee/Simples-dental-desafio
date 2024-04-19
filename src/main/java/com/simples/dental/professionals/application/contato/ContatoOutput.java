package com.simples.dental.professionals.application.contato;

import com.simples.dental.professionals.domain.contato.Contato;

import java.time.LocalDate;

public record ContatoOutput(
        String id,
        String nome,
        String contato,
        String profissionalId,
        LocalDate createdDate
) {
    public static ContatoOutput from(Contato aContato) {
        return new ContatoOutput(
                aContato.getId().getValue(),
                aContato.getNome(),
                aContato.getContato(),
                aContato.getProfissionalId(),
                aContato.getCreatedDate()
        );
    }
}
