package com.simples.dental.professionals.application.profissional.update;

import com.simples.dental.professionals.application.profissional.ProfissionalOutput;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;

import static com.simples.dental.professionals.application.Helpers.notFound;

public class DefaultUpdateProfissionalUseCase extends UpdateProfissionalUseCase {

    private final ProfissionalGateway profissionalGateway;

    public DefaultUpdateProfissionalUseCase(ProfissionalGateway profissionalGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public ProfissionalOutput execute(UpdateProfissionalCommand command) {
        final var profissionalId = IdProfissional.from(command.id());
        final var profissional = this.profissionalGateway
                .findById(profissionalId)
                .orElseThrow(notFound(Profissional.class, profissionalId.getValue()));

        profissional.update(
                command.nome(),
                command.cargo(),
                command.nascimento(),
                profissional.isActive()
        );

        return ProfissionalOutput.with(profissionalGateway.update(profissional));
    }
}
