package com.simples.dental.professionals.infrastructure.contato.presenters;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.infrastructure.contato.models.ContatoResponse;

public interface ContatoPresenter {

    static ContatoResponse present(ContatoOutput contatoOutput) {
        return new ContatoResponse(
                contatoOutput.id(),
                contatoOutput.nome(),
                contatoOutput.contato(),
                contatoOutput.profissional()
        );
    }
}
