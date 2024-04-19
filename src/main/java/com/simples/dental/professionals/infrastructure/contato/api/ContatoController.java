package com.simples.dental.professionals.infrastructure.contato.api;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.application.contato.create.CreateContatoCommand;
import com.simples.dental.professionals.application.contato.create.CreateContatoUseCase;
import com.simples.dental.professionals.application.contato.delete.DeleteContatoUseCase;
import com.simples.dental.professionals.application.contato.retrieve.get.GetContatoByIdUseCase;
import com.simples.dental.professionals.application.contato.update.UpdateContatoCommand;
import com.simples.dental.professionals.application.contato.update.UpdateContatoUseCase;
import com.simples.dental.professionals.infrastructure.profissional.models.CreateOrUpdateContatoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class ContatoController implements ContatoApi {

    private final CreateContatoUseCase createContatoUseCase;

    private final GetContatoByIdUseCase getContatoByIdUseCase;

    private final UpdateContatoUseCase updateContatoUseCase;

    private final DeleteContatoUseCase deleteContatoUseCase;

    public ContatoController(
            CreateContatoUseCase createContatoUseCase,
            GetContatoByIdUseCase getContatoByIdUseCase,
            UpdateContatoUseCase updateContatoUseCase,
            DeleteContatoUseCase deleteContatoUseCase
    ) {
        this.createContatoUseCase = Objects.requireNonNull(createContatoUseCase);
        this.getContatoByIdUseCase = Objects.requireNonNull(getContatoByIdUseCase);
        this.updateContatoUseCase = Objects.requireNonNull(updateContatoUseCase);
        this.deleteContatoUseCase = Objects.requireNonNull(deleteContatoUseCase);
    }

    @Override
    public ResponseEntity<ContatoOutput> createContato(CreateOrUpdateContatoInput input) {
        final var command = CreateContatoCommand.with(input.nome(), input.contato(), input.profissional_id());
        final var contato = this.createContatoUseCase.execute(command);
        return ResponseEntity.created(URI.create("/contatos" + contato.id())).body(contato);
    }

    @Override
    public ContatoOutput getById(String id) {
        return this.getContatoByIdUseCase.execute(id);
    }

    @Override
    public ResponseEntity<ContatoOutput> updateContato(String id, CreateOrUpdateContatoInput input) {
        final var command = UpdateContatoCommand.with(id, input.nome(), input.contato(), input.profissional_id());
        return ResponseEntity.ok(this.updateContatoUseCase.execute(command));
    }

    @Override
    public ResponseEntity<Void> deleteContato(String id) {
        this.deleteContatoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
