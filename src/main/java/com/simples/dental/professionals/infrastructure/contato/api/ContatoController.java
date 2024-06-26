package com.simples.dental.professionals.infrastructure.contato.api;

import com.simples.dental.professionals.application.contato.create.CreateContatoCommand;
import com.simples.dental.professionals.application.contato.create.CreateContatoUseCase;
import com.simples.dental.professionals.application.contato.delete.DeleteContatoUseCase;
import com.simples.dental.professionals.application.contato.retrieve.get.GetContatoByIdUseCase;
import com.simples.dental.professionals.application.contato.retrieve.list.ListContatosUseCase;
import com.simples.dental.professionals.application.contato.update.UpdateContatoCommand;
import com.simples.dental.professionals.application.contato.update.UpdateContatoUseCase;
import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.infrastructure.helpers.ControllerHelpers;
import com.simples.dental.professionals.infrastructure.contato.models.CreateContatoInput;
import com.simples.dental.professionals.infrastructure.contato.models.ContatoResponse;
import com.simples.dental.professionals.infrastructure.contato.models.UpdateContatoInput;
import com.simples.dental.professionals.infrastructure.contato.presenters.ContatoPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.simples.dental.professionals.infrastructure.helpers.ControllerHelpers.validatePaginationValues;

@RestController
public class ContatoController implements ContatoApi {

    private final CreateContatoUseCase createContatoUseCase;

    private final GetContatoByIdUseCase getContatoByIdUseCase;

    private final ListContatosUseCase listContatosUseCase;

    private final UpdateContatoUseCase updateContatoUseCase;

    private final DeleteContatoUseCase deleteContatoUseCase;

    public ContatoController(
            CreateContatoUseCase createContatoUseCase,
            GetContatoByIdUseCase getContatoByIdUseCase, ListContatosUseCase listContatosUseCase,
            UpdateContatoUseCase updateContatoUseCase,
            DeleteContatoUseCase deleteContatoUseCase
    ) {
        this.createContatoUseCase = Objects.requireNonNull(createContatoUseCase);
        this.getContatoByIdUseCase = Objects.requireNonNull(getContatoByIdUseCase);
        this.listContatosUseCase = Objects.requireNonNull(listContatosUseCase);
        this.updateContatoUseCase = Objects.requireNonNull(updateContatoUseCase);
        this.deleteContatoUseCase = Objects.requireNonNull(deleteContatoUseCase);
    }

    @Override
    public ResponseEntity<ContatoResponse> createContato(CreateContatoInput input) {
        final var command = CreateContatoCommand.with(input.nome(), input.contato(), input.profissional_id());
        final var contato = this.createContatoUseCase.execute(command);

        final var contatoResponse = ContatoPresenter.present(contato);
        return ResponseEntity.created(URI.create("/contatos" + contato.id())).body(contatoResponse);
    }

    @Override
    public ContatoResponse getById(String id) {
        final var contato = this.getContatoByIdUseCase.execute(id);
        return ContatoPresenter.present(contato);
    }

    @Override
    public Pagination<Map<String, String>> listContatos(int page, int perPage, String q, List<String> fields) {
        validatePaginationValues(page, perPage);
        fields = ControllerHelpers.fieldsMapper(Contato.class, fields);
        final var query = SearchQuery.with(page, perPage, q, fields);
        return listContatosUseCase.execute(query);
    }

    @Override
    public ResponseEntity<ContatoResponse> updateContato(String id, UpdateContatoInput input) {
        final var command = UpdateContatoCommand.with(id, input.nome(), input.contato(), input.profissional_id());
        final var contato = this.updateContatoUseCase.execute(command);
        return ResponseEntity.ok(ContatoPresenter.present(contato));
    }

    @Override
    public ResponseEntity<Void> deleteContato(String id) {
        this.deleteContatoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
