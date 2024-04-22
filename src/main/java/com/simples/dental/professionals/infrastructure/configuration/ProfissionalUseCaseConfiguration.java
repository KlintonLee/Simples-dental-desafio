package com.simples.dental.professionals.infrastructure.configuration;

import com.simples.dental.professionals.application.profissional.create.CreateProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.create.DefaultCreateProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.delete.DefaultDeleteProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.delete.DeleteProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.retrieve.get.DefaultGetProfissionalByIdUseCase;
import com.simples.dental.professionals.application.profissional.retrieve.get.GetProfissionalByIdUseCase;
import com.simples.dental.professionals.application.profissional.retrieve.list.DefaultListProfissionaisUseCase;
import com.simples.dental.professionals.application.profissional.retrieve.list.ListProfissionaisUseCase;
import com.simples.dental.professionals.application.profissional.update.DefaultUpdateProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.update.UpdateProfissionalUseCase;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ProfissionalUseCaseConfiguration {

    private final ProfissionalGateway profissionalGateway;

    private final ContatoGateway contatoGateway;

    public ProfissionalUseCaseConfiguration(ProfissionalGateway profissionalGateway, ContatoGateway contatoGateway) {
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
    }

    @Bean
    public CreateProfissionalUseCase createProfissionalUseCase() {
        return new DefaultCreateProfissionalUseCase(profissionalGateway);
    }

    @Bean
    public GetProfissionalByIdUseCase getProfissionalByIdUseCase() {
        return new DefaultGetProfissionalByIdUseCase(profissionalGateway, contatoGateway);
    }

    @Bean
    public ListProfissionaisUseCase listProfissionaisUseCase() {
        return new DefaultListProfissionaisUseCase(profissionalGateway);
    }

    @Bean
    public UpdateProfissionalUseCase updateProfissionalUseCase() {
        return new DefaultUpdateProfissionalUseCase(profissionalGateway);
    }

    @Bean
    public DeleteProfissionalUseCase deleteProfissionalUseCase() {
        return new DefaultDeleteProfissionalUseCase(profissionalGateway);
    }
}
