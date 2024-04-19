package com.simples.dental.professionals.infrastructure.api.profissional;

import com.simples.dental.professionals.exceptions.UnprocessableEntityException;
import com.simples.dental.professionals.infrastructure.presenters.ProfissionalOutput;
import com.simples.dental.professionals.application.profissional.create.CreateProfissionalCommand;
import com.simples.dental.professionals.application.profissional.create.CreateProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.delete.DeleteProfissionalUseCase;
import com.simples.dental.professionals.application.profissional.retrieve.get.GetProfissionalByIdUseCase;
import com.simples.dental.professionals.application.profissional.update.UpdateProfissionalCommand;
import com.simples.dental.professionals.application.profissional.update.UpdateProfissionalUseCase;
import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.infrastructure.api.ProfissionalApi;
import com.simples.dental.professionals.infrastructure.models.CreateOrUpdateProfessionalInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Supplier;

@RestController
public class ProfissionalController implements ProfissionalApi {

    private final CreateProfissionalUseCase createUseCase;

    private final GetProfissionalByIdUseCase getByIdUseCase;

    private final UpdateProfissionalUseCase updateUseCase;

    private final DeleteProfissionalUseCase deleteUseCase;

    public ProfissionalController(
            final CreateProfissionalUseCase createUseCase,
            final GetProfissionalByIdUseCase getByIdUseCase,
            final UpdateProfissionalUseCase updateUseCase,
            final DeleteProfissionalUseCase deleteUseCase
    ) {
        this.createUseCase = Objects.requireNonNull(createUseCase);
        this.getByIdUseCase = Objects.requireNonNull(getByIdUseCase);
        this.updateUseCase = Objects.requireNonNull(updateUseCase);
        this.deleteUseCase = Objects.requireNonNull(deleteUseCase);
    }

    @Override
    public ResponseEntity<ProfissionalOutput> createProfissional(CreateOrUpdateProfessionalInput input) {
        final var cargo = serializeCargo(input.cargo());
        final var command = CreateProfissionalCommand.with(input.nome(), cargo, input.nascimento());

        final var profissional = createUseCase.execute(command);
        return ResponseEntity.created(URI.create("/profissionais/" + profissional.id())).build();
    }

    @Override
    public ProfissionalOutput getById(String id) {
        return getByIdUseCase.execute(id);
    }

    @Override
    public ResponseEntity<ProfissionalOutput> updateProfissional(String id, CreateOrUpdateProfessionalInput input) {
        final var cargo = serializeCargo(input.cargo());
        final var command = UpdateProfissionalCommand.with(id, input.nome(), cargo, input.nascimento());

        final var profissional = updateUseCase.execute(command);
        return ResponseEntity.ok(profissional);
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
