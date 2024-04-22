package com.simples.dental.professionals.application.profissional.retrieve.get;

import com.simples.dental.professionals.application.profissional.ProfissionalOutput;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;

import static com.simples.dental.professionals.application.Helpers.notFound;

public class DefaultGetProfissionalByIdUseCase extends GetProfissionalByIdUseCase {

    private final ProfissionalGateway profissionalGateway;

    public DefaultGetProfissionalByIdUseCase(ProfissionalGateway profissionalGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Override
    public ProfissionalOutput execute(String id) {
        final var profissionalId = IdProfissional.from(id);
        final var profissional = profissionalGateway
                .findById(profissionalId)
                .orElseThrow(notFound(Profissional.class, id));

        return ProfissionalOutput.with(profissional);
    }
}
