package com.simples.dental.professionals.application.contato;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.application.profissional.ProfissionalOutput;

import java.time.LocalDate;

public record ContatoOutput(
        String id,
        String nome,
        String contato,
        ProfissionalOutput profissional,
        LocalDate createdDate
) {
    public static ContatoOutput from(Contato aContato) {
        return new ContatoOutput(
                aContato.getId().getValue(),
                aContato.getNome(),
                aContato.getContato(),
                ProfissionalOutput.with(aContato.getProfissional()),
                aContato.getCreatedDate()
        );
    }
}
