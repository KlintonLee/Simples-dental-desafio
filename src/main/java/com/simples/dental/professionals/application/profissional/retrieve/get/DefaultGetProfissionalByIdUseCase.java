package com.simples.dental.professionals.application.profissional.retrieve.get;

import com.simples.dental.professionals.application.profissional.ProfissionalOutput;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;

import static com.simples.dental.professionals.application.Helpers.notFound;

public class DefaultGetProfissionalByIdUseCase extends GetProfissionalByIdUseCase {

    private final ProfissionalGateway profissionalGateway;

    private final ContatoGateway contatoGateway;

    public DefaultGetProfissionalByIdUseCase(ProfissionalGateway profissionalGateway, ContatoGateway contatoGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
    }

    @Override
    public ProfissionalWithContactsOutput execute(String id) {
        final var profissionalId = IdProfissional.from(id);
        final var profissional = profissionalGateway
                .findById(profissionalId)
                .orElseThrow(notFound(Profissional.class, id));

        final var contatos = this.contatoGateway.findAllByProfissional(profissionalId);

        return ProfissionalWithContactsOutput.with(profissional, contatos);
    }
}
