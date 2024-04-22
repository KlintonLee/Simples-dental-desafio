package com.simples.dental.professionals.application.contato.create;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;

import static com.simples.dental.professionals.application.Helpers.notFound;

public class DefaultCreateContatoUseCase extends CreateContatoUseCase {

    private final ContatoGateway contatoGateway;

    private final ProfissionalGateway profissionalGateway;

    public DefaultCreateContatoUseCase(ContatoGateway contatoGateway, ProfissionalGateway profissionalGateway) {
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public ContatoOutput execute(CreateContatoCommand command) {
        final var profissionalId = IdProfissional.from(command.profissionalId());
        final var profissional = this.profissionalGateway
                .findById(profissionalId)
                .orElseThrow(notFound(Profissional.class, command.profissionalId()));

        final var contato = Contato
                .newContato(command.nome(), command.contato(), profissional);
        contato.validate();
        return ContatoOutput.from(this.contatoGateway.create(contato));
    }
}
