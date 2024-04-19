package com.simples.dental.professionals.application.profissional.retrieve.get;

import com.simples.dental.professionals.application.exceptions.NotFoundException;
import com.simples.dental.professionals.infrastructure.presenters.ProfissionalOutput;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;

import java.util.Objects;
import java.util.function.Supplier;

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
                .orElseThrow(notFound(id));

        return ProfissionalOutput.with(profissional);
    }

    private static Supplier<NotFoundException> notFound(String id) {
        return () -> new NotFoundException("Profissional com ID %s não foi encontrado".formatted(id));
    }
}
