package com.simples.dental.professionals.infrastructure.profissional.api;

import com.simples.dental.professionals.application.profissional.retrieve.list.ListProfissionaisUseCase;
import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.exceptions.UnprocessableEntityException;
import com.simples.dental.professionals.infrastructure.helpers.ControllerHelpers;
import com.simples.dental.professionals.application.profissional.create.CreateProfissionalCommand;
import com.simples.dental.professionals.application.profissional.create.CreateProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.delete.DeleteProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.retrieve.get.GetProfissionalByIdUseCase;
import com.simples.dental.professionals.application.profissional.update.UpdateProfissionalCommand;
import com.simples.dental.professionals.application.profissional.update.UpdateProfissionalUseCase;
import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.infrastructure.profissional.models.CreateProfissionalInput;
import com.simples.dental.professionals.infrastructure.profissional.models.ProfissionalResponse;
import com.simples.dental.professionals.infrastructure.profissional.models.UpdateProfissionalInput;
import com.simples.dental.professionals.infrastructure.profissional.presenters.ProfissionalPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static com.simples.dental.professionals.infrastructure.helpers.ControllerHelpers.formatDate;

@RestController
public class ProfissionalController implements ProfissionalApi {

    private final CreateProfissionalUseCase createUseCase;

    private final GetProfissionalByIdUseCase getByIdUseCase;

    private final ListProfissionaisUseCase listUseCase;

    private final UpdateProfissionalUseCase updateUseCase;

    private final DeleteProfissionalUseCase deleteUseCase;

    public ProfissionalController(
            final CreateProfissionalUseCase createUseCase,
            final GetProfissionalByIdUseCase getByIdUseCase,
            final ListProfissionaisUseCase listUseCase,
            final UpdateProfissionalUseCase updateUseCase,
            final DeleteProfissionalUseCase deleteUseCase
    ) {
        this.createUseCase = Objects.requireNonNull(createUseCase);
        this.getByIdUseCase = Objects.requireNonNull(getByIdUseCase);
        this.listUseCase = Objects.requireNonNull(listUseCase);
        this.updateUseCase = Objects.requireNonNull(updateUseCase);
        this.deleteUseCase = Objects.requireNonNull(deleteUseCase);
    }

    @Override
    public ResponseEntity<ProfissionalResponse> createProfissional(CreateProfissionalInput input) {
        final var cargo = serializeCargo(input.cargo());
        final var nascimento = formatDate(input.nascimento());
        final var command = CreateProfissionalCommand.with(input.nome(), cargo, nascimento);

        final var profissional = createUseCase.execute(command);
        final var profissionalResponse = ProfissionalPresenter.present(profissional);
        return ResponseEntity.created(URI.create("/profissionais/" + profissional.id())).body(profissionalResponse);
    }

    @Override
    public ProfissionalResponse getById(String id) {
        return ProfissionalPresenter.present(getByIdUseCase.execute(id));
    }

    @Override
    public Pagination<Map<String, String>> listProfissionais(int page, int perPage, String q, List<String> fields) {
        fields = ControllerHelpers.fieldsMapper(Profissional.class, fields);
        final var query = SearchQuery.with(page, perPage, q, fields);
        return listUseCase.execute(query);
    }

    @Override
    public ResponseEntity<ProfissionalResponse> updateProfissional(String id, UpdateProfissionalInput input) {
        final var cargo = serializeCargo(input.cargo());
        final var nascimento = formatDate(input.nascimento());
        final var command = UpdateProfissionalCommand.with(id, input.nome(), cargo, nascimento);

        final var profissional = updateUseCase.execute(command);
        return ResponseEntity.ok(ProfissionalPresenter.present(profissional));
    }

    @Override
    public ResponseEntity<Void> deleteProfissional(String id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    private static Supplier<UnprocessableEntityException> invalidRole() {
        return () -> new UnprocessableEntityException("Cargo informado inválido, utilize uma das opções %s.".formatted(CargoProfissional.listNames()));
    }

    private static CargoProfissional serializeCargo(String cargo) {
        return CargoProfissional
                .findByRole(cargo)
                .orElseThrow(invalidRole());
    }
}
