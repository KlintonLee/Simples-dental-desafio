package com.simples.dental.professionals.infrastructure.contato.api;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.application.contato.create.CreateContatoCommand;
import com.simples.dental.professionals.application.contato.create.CreateContatoUseCase;
import com.simples.dental.professionals.application.contato.retrieve.get.GetContatoByIdUseCase;
import com.simples.dental.professionals.infrastructure.profissional.models.CreateContatoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class ContatoController implements ContatoApi {

    private final CreateContatoUseCase createContatoUseCase;

    private final GetContatoByIdUseCase getContatoByIdUseCase;

    public ContatoController(CreateContatoUseCase createContatoUseCase, GetContatoByIdUseCase getContatoByIdUseCase) {
        this.createContatoUseCase = Objects.requireNonNull(createContatoUseCase);
        this.getContatoByIdUseCase = Objects.requireNonNull(getContatoByIdUseCase);
    }

    @Override
    public ResponseEntity<ContatoOutput> createContato(CreateContatoInput input) {
        final var command = CreateContatoCommand.with(input.nome(), input.contato(), input.profissional_id());
        final var contato = this.createContatoUseCase.execute(command);
        return ResponseEntity.created(URI.create("/contatos" + contato.id())).body(contato);
    }

    @Override
    public ContatoOutput getById(String id) {
        return this.getContatoByIdUseCase.execute(id);
    }
}
