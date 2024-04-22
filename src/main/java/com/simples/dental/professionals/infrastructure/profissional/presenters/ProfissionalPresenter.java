package com.simples.dental.professionals.infrastructure.profissional.presenters;

import com.simples.dental.professionals.application.profissional.ProfissionalOutput;
import com.simples.dental.professionals.application.profissional.retrieve.get.ProfissionalWithContactsOutput;
import com.simples.dental.professionals.infrastructure.profissional.models.ContatoForProfissonalResponse;
import com.simples.dental.professionals.infrastructure.profissional.models.ProfissionalContatosResponse;
import com.simples.dental.professionals.infrastructure.profissional.models.ProfissionalResponse;

import java.util.stream.Collectors;

public interface ProfissionalPresenter {

    static ProfissionalResponse present(ProfissionalOutput profissionalOutput) {
        return new ProfissionalResponse(
                profissionalOutput.id(),
                profissionalOutput.nome(),
                profissionalOutput.cargo().toString().toLowerCase(),
                profissionalOutput.nascimento(),
                profissionalOutput.active(),
                profissionalOutput.createdDate()
        );
    }

    static ProfissionalContatosResponse present(ProfissionalWithContactsOutput output) {
        final var contatos = output.contatos()
                .stream()
                .map(contato -> new ContatoForProfissonalResponse(
                        contato.getId().getValue(),
                        contato.getNome(),
                        contato.getContato()
                )
        ).collect(Collectors.toList());

        return new ProfissionalContatosResponse(
                output.id(),
                output.nome(),
                output.cargo().toString().toLowerCase(),
                output.nascimento(),
                contatos,
                output.active(),
                output.createdDate()
        );
    }
}
