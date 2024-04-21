package com.simples.dental.professionals.infrastructure.configuration;

import com.simples.dental.professionals.application.contato.create.CreateContatoUseCase;
import com.simples.dental.professionals.application.contato.create.DefaultCreateContatoUseCase;
import com.simples.dental.professionals.application.contato.delete.DefaultDeleteContatoUseCase;
import com.simples.dental.professionals.application.contato.delete.DeleteContatoUseCase;
import com.simples.dental.professionals.application.contato.retrieve.get.DefaultGetContatoByIdUseCase;
import com.simples.dental.professionals.application.contato.retrieve.get.GetContatoByIdUseCase;
import com.simples.dental.professionals.application.contato.retrieve.list.DefaultListContatosUseCase;
import com.simples.dental.professionals.application.contato.retrieve.list.ListContatosUseCase;
import com.simples.dental.professionals.application.contato.update.DefaultUpdateContatoUseCase;
import com.simples.dental.professionals.application.contato.update.UpdateContatoUseCase;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ContatoUseCaseConfiguration {

    private final ContatoGateway contatoGateway;

    private final ProfissionalGateway profissionalGateway;

    public ContatoUseCaseConfiguration(ContatoGateway contatoGateway, ProfissionalGateway profissionalGateway) {
        this.contatoGateway = Objects.requireNonNull(contatoGateway);
        this.profissionalGateway = Objects.requireNonNull(profissionalGateway);
    }

    @Bean
    public CreateContatoUseCase createContatoUseCase() {
        return new DefaultCreateContatoUseCase(contatoGateway, profissionalGateway);
    }

    @Bean
    public GetContatoByIdUseCase getContatoByIdUseCase() {
        return new DefaultGetContatoByIdUseCase(contatoGateway);
    }

    @Bean
    public ListContatosUseCase listContatosUseCase() {
        return new DefaultListContatosUseCase(contatoGateway);
    }

    @Bean
    public UpdateContatoUseCase updateContatoUseCase() {
        return new DefaultUpdateContatoUseCase(contatoGateway, profissionalGateway);
    }

    @Bean
    public DeleteContatoUseCase deleteContatoUseCase() {
        return new DefaultDeleteContatoUseCase(contatoGateway);
    }
}
